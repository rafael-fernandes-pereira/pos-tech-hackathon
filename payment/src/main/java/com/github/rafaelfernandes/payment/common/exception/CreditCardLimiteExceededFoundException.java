package com.github.rafaelfernandes.payment.common.exception;

public class CreditCardLimiteExceededFoundException extends RuntimeException {

    private static final String MESSAGE = "Limite do cartão de crédito excedido";

    private static Integer STATUS = 402;

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
