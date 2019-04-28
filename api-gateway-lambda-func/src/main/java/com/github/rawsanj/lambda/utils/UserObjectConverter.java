package com.github.rawsanj.lambda.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rawsanj.lambda.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UserObjectConverter {

	private final static Logger logger = LogManager.getLogger(UserObjectConverter.class);

	public static User toUserObject(String userSting) {
		User user = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			user = objectMapper.readValue(userSting, User.class);
		} catch (IOException e) {
			logger.error("Error Occurred", e);
		}
		return user;
	}

	public static String toUserString(User user) {
		ObjectMapper objectMapper = new ObjectMapper();
		String userString = "";
		try {
			userString = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			logger.error("Error Occurred", e);
		}
		return userString;
	}

	public static String toUserString(List<User> user) {
		ObjectMapper objectMapper = new ObjectMapper();
		String userString = "";
		try {
			userString = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			logger.error("Error Occurred", e);
		}
		return userString;
	}
}
