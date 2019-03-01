package sample.com.github.rawsanj.code;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.APIGatewayRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LambdaHandler extends APIGatewayRequestHandler {

	private final static Logger logger = LogManager.getLogger(LambdaHandler.class);

	@Override
	public APIGatewayProxyResponseEvent processRequest(APIGatewayProxyRequestEvent input, Context context) {

		logger.debug("I am inside LAMBDA!" + ">>>" + input.getBody());

		response.setBody(input.getBody());

		return response;
	}
}
