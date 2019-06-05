package com.github.rawsanj.lambda.filters;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;
import com.github.rawsanj.annotation.FunctionFilter;
import com.github.rawsanj.lambda.domain.User;
import com.github.rawsanj.lambda.utils.UserObjectConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FunctionFilter(order = 2)
public class SecondValidateUserFilter implements Filter {

	private final static Logger logger = LogManager.getLogger(SecondValidateUserFilter.class);

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {

		String httpMethod = request.getHttpMethod();
		boolean isRequestValid = false;

		if (httpMethod.equalsIgnoreCase("POST") || httpMethod.equalsIgnoreCase("PUT") ) {
			User user = UserObjectConverter.toUserObject(request.getBody());

			logger.info("Checking if the User Name in the body: {} is valid.", user.toString());

			Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(user.getUserName());

			if (matcher.find()) {
				response.setBody("Invalid Request. Username must not contain any special characters, only String and Numners are allowed.");
				response.setStatusCode(400);
				logger.info("Invalid Request. Username: {} container special characters.", user.getUserName());
			} else {
				isRequestValid = true;
			}

		} else {
			isRequestValid = true;
		}

		if (isRequestValid) {
			logger.info("Request is validated.");
			chain.doFilter(request, response, chain);
		} else {
			response.setStatusCode(500);
			response.setBody("Request Rejected");
			logger.error("Invalid Request");
		}
	}
}
