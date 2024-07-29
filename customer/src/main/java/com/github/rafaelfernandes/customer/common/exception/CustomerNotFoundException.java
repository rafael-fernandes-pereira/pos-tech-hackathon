package com.github.rafaelfernandes.customer.common.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Cliente não encontrado";

    private static Integer STATUS = 404;

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
