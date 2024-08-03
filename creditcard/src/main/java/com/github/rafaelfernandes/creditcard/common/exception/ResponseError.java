package com.github.rafaelfernandes.creditcard.common.exception;

public record ResponseError(
        String message, Integer status
) {

}