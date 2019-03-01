package com.github.rawsanj.code;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;

public class ThirdFilter implements Filter {

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {
		request.setBody("THIRD >>" + request.getBody());

		chain.doFilter(request, response, chain);

		response.setBody("THIRD >>" + response.getBody());
	}

	@Override
	public Integer order() {
		return 3;
	}
}
