package com.github.rafaelfernandes.creditcard.common.exception;

public class CreditCardLimiteExceededFoundException extends RuntimeException {

    private static final String MESSAGE = "Limite do cartão de crédito excedido";

    private static Integer STATUS = 500;

    public CreditCardLimiteExceededFoundException() {
        super(MESSAGE);
    }

    public int getStatus() {
        return STATUS;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
