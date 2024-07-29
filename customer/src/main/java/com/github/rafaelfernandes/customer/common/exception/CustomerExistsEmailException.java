package com.github.rafaelfernandes.customer.common.exception;

public class CustomerExistsEmailException extends RuntimeException{

    private static final String MESSAGE = "Email já cadastrado";

    private static Integer STATUS = 500;

    public CustomerExistsEmailException() {
        super(MESSAGE);
    }

    public int getStatus() {
        return STATUS;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
