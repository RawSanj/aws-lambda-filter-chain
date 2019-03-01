package com.github.rawsanj.code;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.APIGatewayRequestHandler;

public class LambdaHandler extends APIGatewayRequestHandler {

	@Override
	public APIGatewayProxyResponseEvent processRequest(APIGatewayProxyRequestEvent input, Context context) {

		System.out.println("I am inside LAMBDA!" + ">>>" + input.getBody());

		response.setBody(input.getBody());

		return response;
	}
}
