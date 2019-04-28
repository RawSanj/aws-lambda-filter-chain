package com.github.rawsanj;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class APIGatewayRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private final FilterChain filterChain = new FilterChain();

	public APIGatewayRequestHandler() {

		try {
			Class<?> cls = Class.forName("com.github.rawsanj.util.FilterRegistrator");
			Object object = cls.getDeclaredConstructor().newInstance();

			Class[] noparams = {};
			Method method = cls.getDeclaredMethod("getRegisteredFilters", noparams);
			List<Filter> filters = (List<Filter>) method.invoke(object, null);
			filters.forEach(filterChain::addFilter);
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

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
