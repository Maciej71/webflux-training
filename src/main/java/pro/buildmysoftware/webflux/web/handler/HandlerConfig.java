package pro.buildmysoftware.webflux.web.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class HandlerConfig {

	@Bean
	public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {
		return RouterFunctions
			.route(GET("/hello"), helloHandler::hello);
	}
}
