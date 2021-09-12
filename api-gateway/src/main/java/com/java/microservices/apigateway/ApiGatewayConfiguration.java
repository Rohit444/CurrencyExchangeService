package com.java.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator getwayRouter(RouteLocatorBuilder builder) {
		// return builder.routes().build(); // this is when we do not customize any
		// route here
		return builder.routes().route(fn -> fn.path("/get")
				.filters(f->f.addRequestHeader("MyHeader", "MyURI")
						      .addRequestParameter("Param", "MyValue"))
				.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
							 .uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						 .uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**")
						 .uri("lb://currency-conversion"))
				.build();
	}

}
