package com.github.rawsanj.lambda.filters;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;
import com.github.rawsanj.annotation.FunctionFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@FunctionFilter(order = 3)
public class ThirdJustAnotherFilter implements Filter {

	private final static Logger logger = LogManager.getLogger(ThirdJustAnotherFilter.class);

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {

		logger.info("This is the Third Filter");
		chain.doFilter(request, response, chain);
		logger.info("Response from 3rd filter: {}",response.getBody());
	}
}
