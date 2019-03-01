package sample.com.github.rawsanj.code;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThirdFilter implements Filter {

	private final static Logger logger = LogManager.getLogger(ThirdFilter.class);

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {
		request.setBody("IN-THIRD >>" + request.getBody());

		logger.debug("IN >>>>>> NAME: "+ this.getClass().getName() + ". Order"+ this.order() + ". THREAD: " + Thread.currentThread().getName() + ". REQUEST: "+ request.getBody() + ". RESPONSE: " + response.getBody());

		chain.doFilter(request, response, chain);

		logger.debug("OUT <<<<<< NAME: "+ this.getClass().getName() + ". Order"+ this.order() + ". THREAD: " + Thread.currentThread().getName() + ". REQUEST: "+ request.getBody() + ". RESPONSE: " + response.getBody());

		response.setBody("OUT-THIRD >>" + response.getBody());
	}

	@Override
	public Integer order() {
		return 3;
	}
}
