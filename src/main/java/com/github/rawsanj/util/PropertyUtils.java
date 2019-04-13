package com.github.rawsanj.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

	private static final Properties properties = new Properties();

	private static final Logger logger = LogManager.getLogger(PropertyUtils.class);

	private static String propertiesFileName = "src/main/resources/application.properties";

	public static void initPropertyValues() {

		String[] tmpArray = propertiesFileName.split("/");
		String propFileName = tmpArray[tmpArray.length - 1];

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = loader.getResourceAsStream(propFileName);

			if (inputStream != null) {
				properties.load(inputStream);
			}
		} catch (FileNotFoundException e) {
			logger.info("Property file '" + propFileName + "' not found in the classpath");
		} catch (IOException e) {
			logger.fatal("Exception to load file " + e.getMessage());
		}

	}

	public static void initPropertyValues(String propertiesFilePath) {

		if (propertiesFilePath != null || propertiesFilePath.equalsIgnoreCase("")){
			propertiesFileName = propertiesFilePath;
		}
		initPropertyValues();
	}

	public static Properties getProperties() {
		return properties;
	}

}
