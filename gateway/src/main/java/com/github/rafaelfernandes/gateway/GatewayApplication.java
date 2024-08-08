package com.github.rafaelfernandes.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/login-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://LOGIN-API"))
				.route(r -> r.path("/customer-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://CUSTOMER-API"))
				.route(r -> r.path("/credit-card-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://CREDITCARD-API"))
				.route(r -> r.path("/payment-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://PAYMENT-API"))
				.build();
	}

}
