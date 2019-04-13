package com.github.rawsanj;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public interface Filter {

	void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain);
}
