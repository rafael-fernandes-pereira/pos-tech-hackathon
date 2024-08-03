package com.github.rafaelfernandes.creditcard.common.response;

public record ResponseError(
    String message, Integer status
) {
    
}
