package com.github.rafaelfernandes.payment.common.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Cliente não encontrado";

    private static Integer STATUS = 500;

    public CustomerNotFoundException() {
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
