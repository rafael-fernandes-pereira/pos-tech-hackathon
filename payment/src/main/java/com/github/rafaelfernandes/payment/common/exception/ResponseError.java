package com.github.rafaelfernandes.payment.common.exception;

public record ResponseError(
        String message, Integer status
) {

}