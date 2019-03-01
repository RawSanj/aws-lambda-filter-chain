package com.github.rawsanj;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.reflections.Reflections;

import java.util.Set;

public abstract class APIGatewayRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private final FilterChain filterChain = new FilterChain();

	public APIGatewayRequestHandler() {
		Reflections reflections = new Reflections("com.github.rawsanj");
		Set<Class<? extends Filter>> subTypes = reflections.getSubTypesOf(Filter.class);
		subTypes.forEach(aClass -> {
			try {
				Class cls = Class.forName(aClass.getName());
				Filter f = (Filter) cls.newInstance();
				if (f.order() != null){
					filterChain.addFilter(f);
				}
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
		});
	}

	protected APIGatewayProxyResponseEvent response;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> requestHandler = this::processRequest;
		filterChain.setRequestHandler(requestHandler);

		response = new APIGatewayProxyResponseEvent();

		filterChain.doFilter(input, response, filterChain);

		return response;
	}

	public abstract APIGatewayProxyResponseEvent processRequest(APIGatewayProxyRequestEvent input, Context context);
}
