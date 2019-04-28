package com.github.rawsanj.annotation.processors;


import com.github.rawsanj.annotation.FunctionFilter;
import com.google.auto.service.AutoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.Map.Entry;

@SupportedAnnotationTypes("com.github.rawsanj.annotation.FunctionFilter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class FunctionFilterProcessor extends AbstractProcessor {

	private static final Logger logger = LogManager.getLogger(FunctionFilterProcessor.class);

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		logger.info("ROUND: {}. Processing Over: {}", roundEnv.toString(), roundEnv.processingOver());


		Collection<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(FunctionFilter.class);
		List<TypeElement> types = ElementFilter.typesIn(annotatedElements);

		String packageName = null;

		Map<String, Integer> filterMap = new HashMap<>();

		for (TypeElement type : types) {
			PackageElement packageElement = (PackageElement) type.getEnclosingElement();
			packageName = packageElement.getQualifiedName().toString();
			int filterOrder = type.getAnnotation(FunctionFilter.class).order();
			filterMap.put(type.toString(), filterOrder);
		}

		Map<String, Integer> sortedFilterMap = sortByValue(filterMap);
		List<String> filterList = new ArrayList<>(sortedFilterMap.keySet());

		if (packageName == null) {
			return false;
		}

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("filterList", filterList);

		Template template = velocityEngine.getTemplate("template/plainclass.vm");

		JavaFileObject javaFileObject = null;
		try {
			javaFileObject = processingEnv.getFiler().createSourceFile("com.github.rawsanj.util.FilterRegistrator");
		} catch (IOException e) {
			logger.debug("Cannot create Java File. {}", e.getMessage());
			e.printStackTrace();
		}

		try {
			Writer writer = javaFileObject.openWriter();
			template.merge(velocityContext, writer);
			writer.close();
			logger.debug("FilterRegistrator class created successfully.");
		} catch (IOException | NullPointerException e) {
			logger.error("Error writing Java file. Cause: {}", e.getCause());
			e.printStackTrace();
		}

		return false;
	}

	private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
		List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

		// Sorting the list based on values
		list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) == 0
				? o1.getKey().compareTo(o2.getKey())
				: o1.getValue().compareTo(o2.getValue()));
		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	}
}
