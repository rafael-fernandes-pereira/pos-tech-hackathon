package com.github.rafaelfernandes.customer.common.exception;

public record ResponseError(
        String message, Integer status
) {

}