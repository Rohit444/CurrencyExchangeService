package com.java.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	
	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
	@RateLimiter(name="sample-api", fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
	public String sampleAPI() {
//		logger.info("Sample Api Call received");
//		ResponseEntity<String> responseEntiry = new RestTemplate().getForEntity("http://localhost:8080/dummy-url",
//				String.class);
//		return responseEntiry.getBody();
		return "sample-api";
	}
	
	public String hardcodedResponse(Throwable throwable) {
		return " fallback-response "+throwable.getMessage();
	}

}
