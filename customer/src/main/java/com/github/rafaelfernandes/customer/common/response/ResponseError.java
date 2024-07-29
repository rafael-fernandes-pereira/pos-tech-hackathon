package com.github.rafaelfernandes.customer.common.response;

public record ResponseError(
    String message, Integer status
) {
    
}
