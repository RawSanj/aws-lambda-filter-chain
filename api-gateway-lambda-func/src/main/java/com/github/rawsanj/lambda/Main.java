package com.github.rawsanj.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.io.*;


public class Main {

//	private static boolean ASC = true;
//	private static boolean DESC = false;
//
//	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
//	{
//		List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());
//
//		// Sorting the list based on values
//		list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
//				? o1.getKey().compareTo(o2.getKey())
//				: o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
//				? o2.getKey().compareTo(o1.getKey())
//				: o2.getValue().compareTo(o1.getValue()));
//		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
//
//	}
//
//	private static void printMap(Map<String, Integer> map)
//	{
//		map.forEach((key, value) -> System.out.println("Key : " + key + " Value : " + value));
//	}

	public static void main(String[] args) throws IOException {
//
//		Map<String, Integer> unsortedMap = new HashMap<>();
//		unsortedMap.put("AAAA", 1);
//		unsortedMap.put("ZZZ", 9);
//		unsortedMap.put("III", 4);
//		unsortedMap.put("GGG", 3);
//		unsortedMap.put("XXX", 5);
//
//		System.out.println(unsortedMap);
//
//		Map<String, Integer> sortedMap = sortByValue(unsortedMap, ASC);
//
//		System.out.println(sortedMap);
//
//		System.out.println(">>>>>>>>>>>>");
//
//		unsortedMap.keySet().forEach(System.out::println);
//
//		System.out.println(">>>>>>>>>>>>");
//
//		List<String> collect = new ArrayList<>(sortedMap.keySet());
//
//		System.out.println(collect);
//

		ApiGatewayRequestHandler apiGatewayRequestHandler = new ApiGatewayRequestHandler();

		APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
		requestEvent.setBody("<THIS IS REQUEST BODY>");

//		APIGatewayProxyRequestEvent requestEvent2 = new APIGatewayProxyRequestEvent();
//		requestEvent2.setBody("<THIS IS REQUEST BODY>");
//
//		APIGatewayProxyRequestEvent requestEvent3 = new APIGatewayProxyRequestEvent();
//		requestEvent3.setBody("<THIS IS REQUEST BODY>");

//		Runnable r1 = () -> {
//			APIGatewayProxyResponseEvent res1 = apiGatewayRequestHandler.handleRequest(requestEvent, null);
//			System.out.println("THREAD OUTPUT 1: "+ res1.getBody());
//		};
//
//		Runnable r2 = () -> {
//			APIGatewayProxyResponseEvent res2 = apiGatewayRequestHandler.handleRequest(requestEvent2, null);
//			System.out.println("THREAD OUTPUT 2: "+ res2.getBody());
//		};
//
//		Runnable r3 = () -> {
//			APIGatewayProxyResponseEvent res3 = apiGatewayRequestHandler.handleRequest(requestEvent3, null);
//			System.out.println("THREAD OUTPUT 3: "+ res3.getBody());
//		};
//
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



		APIGatewayProxyResponseEvent res1 = apiGatewayRequestHandler.handleRequest(requestEvent, null);
		System.out.println("PLAIN OUTPUT 1: [[" + res1.getBody()+ "]]");

//		APIGatewayProxyResponseEvent res2 = apiGatewayRequestHandler.handleRequest(requestEvent2, null);
//		System.out.println("PLAIN OUTPUT 2: "+ res2.getBody());
//
//		APIGatewayProxyResponseEvent res3 = apiGatewayRequestHandler.handleRequest(requestEvent3, null);
//		System.out.println("PLAIN OUTPUT 3: "+ res3.getBody());

	}
}
