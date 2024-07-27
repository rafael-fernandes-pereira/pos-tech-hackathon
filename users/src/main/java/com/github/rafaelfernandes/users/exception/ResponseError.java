package com.github.rafaelfernandes.users.exception;

public record ResponseError(
    String message, Integer status
) {
    
}
