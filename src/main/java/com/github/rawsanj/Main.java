package com.github.rawsanj;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.code.LambdaHandler;

public class Main {

	public static void main(String[] args) {

		LambdaHandler lambdaHandler = new LambdaHandler();

		APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
		requestEvent.setBody("<THIS IS REQUEST BODY>");

		APIGatewayProxyRequestEvent requestEvent2 = new APIGatewayProxyRequestEvent();
		requestEvent2.setBody("<THIS IS REQUEST BODY>");

		APIGatewayProxyRequestEvent requestEvent3 = new APIGatewayProxyRequestEvent();
		requestEvent3.setBody("<THIS IS REQUEST BODY>");

//		Runnable r1 = () -> {
//			APIGatewayProxyResponseEvent res1 = lambdaHandler.handleRequest(requestEvent, null);
//			System.out.println("FINAL OUTPUT 1: "+ res1.getBody());
//		};
//
//		Runnable r2 = () -> {
//			APIGatewayProxyResponseEvent res2 = lambdaHandler.handleRequest(requestEvent2, null);
//			System.out.println("FINAL OUTPUT 2: "+ res2.getBody());
//		};
//
//		Runnable r3 = () -> {
//			APIGatewayProxyResponseEvent res3 = lambdaHandler.handleRequest(requestEvent3, null);
//			System.out.println("FINAL OUTPUT 3: "+ res3.getBody());
//		};

		APIGatewayProxyResponseEvent res1 = lambdaHandler.handleRequest(requestEvent, null);
		System.out.println("FINAL OUTPUT 1: "+ res1.getBody());

		APIGatewayProxyResponseEvent res2 = lambdaHandler.handleRequest(requestEvent2, null);
		System.out.println("FINAL OUTPUT 2: "+ res2.getBody());

		APIGatewayProxyResponseEvent res3 = lambdaHandler.handleRequest(requestEvent3, null);
		System.out.println("FINAL OUTPUT 3: "+ res3.getBody());

//		Thread th1 = new Thread(r1);
//		Thread th2 = new Thread(r2);
//		Thread th3 = new Thread(r3);
//
//		th1.start();
//
////		try {
////			Thread.sleep(2_000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//
//		th2.start();
//
//
////		try {
////			Thread.sleep(2_000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//
//		th3.start();

	}
}
