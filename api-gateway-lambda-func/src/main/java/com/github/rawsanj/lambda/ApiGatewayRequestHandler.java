package com.github.rawsanj.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.APIGatewayRequestHandler;
import com.github.rawsanj.lambda.domain.User;
import com.github.rawsanj.lambda.repository.UserRepository;
import com.github.rawsanj.lambda.utils.UserObjectConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ApiGatewayRequestHandler extends APIGatewayRequestHandler {

	private final static Logger logger = LogManager.getLogger(ApiGatewayRequestHandler.class);

	@Override
	public APIGatewayProxyResponseEvent processRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

		String httpMethod = requestEvent.getHttpMethod().toUpperCase();
		String responseData = "Unsupported Operation";
		Integer responseStatus = 400;
		User requestUser = null;
		User responseUser = null;

		switch (httpMethod) {
			case "GET":

				String userName = requestEvent.getPath().replaceAll("/user", "");
				if (userName.isEmpty()) {
					List<User> userList = UserRepository.findAllUser();
					responseData = UserObjectConverter.toUserString(userList);
				} else {
					userName = userName.replaceAll("/", "");
					User userByName = UserRepository.findUserByName(userName);
					responseData = UserObjectConverter.toUserString(userByName);
				}
				responseStatus = 200;
				break;

			case "PUT":

				requestUser = UserObjectConverter.toUserObject(requestEvent.getBody());
				responseUser = UserRepository.updateUser(requestUser);
				responseData = UserObjectConverter.toUserString(responseUser);
				responseStatus = 201;
				break;

			case "POST":

				requestUser = UserObjectConverter.toUserObject(requestEvent.getBody());
				responseUser = UserRepository.createUser(requestUser);
				responseData = UserObjectConverter.toUserString(responseUser);
				responseStatus = 201;
				break;

			case "DELETE":

				if (!requestEvent.getPath().replaceAll("/user", "").isEmpty()) {
					String user = requestEvent.getPath().replaceAll("/user/", "");
					List<User> updatedUserList = UserRepository.deleteUser(user);
					responseData = UserObjectConverter.toUserString(updatedUserList);
					responseStatus = 200;
				} else {
					responseData = "User Not Found";
					responseStatus = 400;
				}

				break;

			default:

				logger.info("Unsupported Http Method.");
		}

		response.setBody(responseData);
		response.setStatusCode(responseStatus);

		return response;
	}

}
