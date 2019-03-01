package com.github.rawsanj;


import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {

	private final static List<Filter> filters = new ArrayList<>();
	private Integer index = 0;
	private RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> requestHandler;

	void addFilter(Filter filter) {
		filters.add(filter);
	}

	void setRequestHandler(RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> requestHandler) {
		this.requestHandler = requestHandler;
		setFilterOrder();
	}

	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {

		if (index == filters.size()){
			requestHandler.handleRequest(request, null);
			index = 0;
			return;
		}

		Filter filter = filters.get(index);
		index += 1;
		filter.doFilter(request, response, chain);
	}

	private void setFilterOrder() {
		filters.sort((f1, f2) -> {
			return f1.order() > f2.order() ? 1 : (f1.order() < f2.order()) ? -1 : 0;
		});
	}

	@Override
	public Integer order() {
		return null;
	}
}
