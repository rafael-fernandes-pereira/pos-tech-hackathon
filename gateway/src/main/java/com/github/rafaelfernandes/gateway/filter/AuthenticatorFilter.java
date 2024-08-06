package com.github.rafaelfernandes.gateway.filter;

import com.github.rafaelfernandes.gateway.service.ValidateTokenService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthenticatorFilter implements GatewayFilter  {

    private final RouteValidator routeValidator;

    private final ValidateTokenService validateToken;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            if (isAuthMissing(request)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            var token = getAuthHeader(request);

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            if (!validateToken.validate(token)) {
                return this.onError(exchange, HttpStatus.FORBIDDEN);
            }
        }

        return chain.filter(exchange);


    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

}
