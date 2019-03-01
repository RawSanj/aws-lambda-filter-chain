package com.github.rawsanj;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.util.PropertyUtils;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Set;

public abstract class APIGatewayRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private final FilterChain filterChain = new FilterChain();

	public APIGatewayRequestHandler() {
		// Initialize Application Properties
		PropertyUtils.initPropertyValues();
		Properties properties = PropertyUtils.getProperties();

		// Scan for Filter implementations
		String filterPackage = properties.getProperty("filter.package.name");
		Reflections reflections = new Reflections(filterPackage);
		Set<Class<? extends Filter>> subTypes = reflections.getSubTypesOf(Filter.class);
		subTypes.forEach(aClass -> {
			try {
				Class cls = Class.forName(aClass.getName());
				Filter f = null;
				try {
					f = (Filter) cls.getDeclaredConstructor().newInstance();
				} catch (InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
				if (f.order() != null){
					filterChain.addFilter(f);
				}
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
		});

		// Set the requestHandler to invoke the over-ridden method processRequest()
		RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> requestHandler = this::processRequest;
		filterChain.setRequestHandler(requestHandler);
	}

	protected APIGatewayProxyResponseEvent response;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		response = new APIGatewayProxyResponseEvent();

		filterChain.doFilter(input, response, filterChain);

		return response;
	}

	public abstract APIGatewayProxyResponseEvent processRequest(APIGatewayProxyRequestEvent input, Context context);
}
