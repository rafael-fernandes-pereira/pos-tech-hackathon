package com.github.rafaelfernandes.creditcard.common.exception;

public class CreditCardNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Cartão de crédito não encontrado";

    private static Integer STATUS = 500;

    public CreditCardNotFoundException() {
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
