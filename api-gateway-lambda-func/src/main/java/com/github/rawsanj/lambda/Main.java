package com.github.rawsanj.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Main {


	public static void main(String[] args) throws IOException {

		ApiGatewayRequestHandler apiGatewayRequestHandler = new ApiGatewayRequestHandler();

		apiGatewayRequestHandler.handleRequest(createRequest("sanjay"), null);
		apiGatewayRequestHandler.handleRequest(createRequest("darshit"), null);
		apiGatewayRequestHandler.handleRequest(createRequest("nikita"), null);
		apiGatewayRequestHandler.handleRequest(createRequest("arsh"), null);
	}

	private static APIGatewayProxyRequestEvent createRequest(String username) {
		APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
		requestEvent.setHttpMethod("GET");
		requestEvent.setPath("/user/"+ username);
		return requestEvent;
	}
}
