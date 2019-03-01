package com.github.rawsanj.code;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;

public class SecondFilter implements Filter {

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {
		request.setBody("SECOND >>" + request.getBody());

		chain.doFilter(request, response, chain);

		response.setBody("SECOND >>" + response.getBody());
	}

	@Override
	public Integer order() {
		return 2;
	}
}
