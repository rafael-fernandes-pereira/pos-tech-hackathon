package com.github.rafaelfernandes.gateway.filter;

import com.github.rafaelfernandes.gateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import com.github.rafaelfernandes.gateway.exception.UnauthorizedException;

@Component
public class AuthenticatorFilter extends AbstractGatewayFilterFactory<AuthenticatorFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;

    public AuthenticatorFilter() {
        super(Config.class);

    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                String token = exchange.getRequest().getHeaders().get("Authorization").get(0);

                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }

                try {
                    jwtService.validateToken(token);

                    var role = jwtService.extractRole(token);

                    if (role.equals("CUSTOMER")) {
                        var userId = jwtService.extractUserId(token);
                        request = exchange.getRequest()
                                .mutate()
                                .header("userId", userId.toString())
                                .build();
                    }

                    String requiredRole = routeValidator.getRequiredRole(exchange.getRequest());

                    if (requiredRole != null && !requiredRole.equals(role)) {
                        throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                    }

                } catch (Exception e) {
                    System.out.println("Error in AuthenticationFilter: " + e.getMessage());
                    throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }

}
