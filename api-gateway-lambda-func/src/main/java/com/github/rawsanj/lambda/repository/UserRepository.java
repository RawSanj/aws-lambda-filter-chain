package com.github.rawsanj.lambda.repository;

import com.github.rawsanj.lambda.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* In-Memory User Data Base. Data would be held only till lambda instance is alive.
*/
public class UserRepository {

	private final static Logger logger = LogManager.getLogger(UserRepository.class);

	private final static Map<String, User> USER_MAP = new HashMap<>();

	static {
		USER_MAP.put("sanjay", new User("sanjay", "Sanjay Rawat", 29, "Capgemini"));
		USER_MAP.put("darshit", new User("darshit", "Darshit Sanghavi", 27, "Capgemini"));
		USER_MAP.put("nikita", new User("nikita", "Nikita Asnani", 25, "Capgemini"));
		USER_MAP.put("arsh", new User("arsh", "Arsh Bhardwaj", 28, "Capgemini"));
	}

	public static User findUserByName(String userName) {
		return USER_MAP.get(userName);
	}

	public static List<User> findAllUser() {
		return new ArrayList<>(USER_MAP.values());
	}

	public static User createUser(User user) {
		User userInDb =  findUserByName(user.getUserName());
		if (userInDb != null) {
			logger.info("User Already exists");
			return userInDb;
		} else {
			USER_MAP.put(user.getUserName(), user);
			User savedUser = USER_MAP.get(user.getUserName());
			logger.info("User saved: {}", savedUser.toString());
			return savedUser;
		}
	}

	public static User updateUser(User user) {
		User userInDb = findUserByName(user.getUserName());
		if (userInDb != null) {
			USER_MAP.put(user.getUserName(), user);
			return findUserByName(user.getUserName());
		} else {
			logger.info("User '{}' doesn't exists", user.getUserName());
			return null;
		}
	}

	public static List<User> deleteUser(String userName) {
		USER_MAP.remove(userName);
		return findAllUser();
	}
}
