package com.github.rafaelfernandes.gateway.filter;


import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public String getRequiredRole(ServerHttpRequest request) {
        var path = request.getPath().toString();

        if (path.startsWith("/products")) {
            switch (request.getMethod().name()) {
                case "POST":
                    return "ADMINISTRATOR";
                case "PUT":
                case "DELETE":
                    if (path.matches("/products/\\s+")) {
                        return "ADMINISTRATOR";
                    }
                    break;
                default:
                    break;
            }
        }

        return null;
    }

    public static final List<String> openApiEndpoints = List.of(
            "/login/authenticate",
            "/login/validate",
            "/users/register",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
