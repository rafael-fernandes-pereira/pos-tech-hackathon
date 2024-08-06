package com.github.rafaelfernandes.payment.common.response;

public record ResponseError(
    String message, Integer status
) {
    
}
