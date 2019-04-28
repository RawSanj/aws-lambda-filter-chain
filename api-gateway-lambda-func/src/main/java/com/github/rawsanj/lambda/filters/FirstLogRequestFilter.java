package com.github.rawsanj.lambda.filters;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;
import com.github.rawsanj.annotation.FunctionFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@FunctionFilter(order = 1)
public class FirstLogRequestFilter implements Filter {

	private final static Logger logger = LogManager.getLogger(FirstLogRequestFilter.class);

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {

		logger.info("ApiGateway Request Body: {}. Http Method: {}. Api Path: {}", request.getBody(), request.getHttpMethod(), request.getPath());

		chain.doFilter(request, response, chain);

		logger.info("ApiGateway Response: {}. Http Status: {}", response.getBody(), response.getStatusCode());

	}
}
