package sample.com.github.rawsanj.code;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.rawsanj.Filter;
import com.github.rawsanj.FilterChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FirstFilter implements Filter {

	private final static Logger logger = LogManager.getLogger(FirstFilter.class);

	@Override
	public void doFilter(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, FilterChain chain) {
		request.setBody("FIRST >>" + request.getBody());

		logger.debug("NAME: "+ this.getClass().getName() + ". Order"+ this.order() + ". THREAD: " + Thread.currentThread().getName());

		chain.doFilter(request, response, chain);

		response.setBody("FIRST >>" + response.getBody());
	}

	@Override
	public Integer order() {
		return 1;
	}
}
