package com.github.rafaelfernandes.customer.common.exception;

public class CustomerExistsCpfException extends RuntimeException{

    private static final String MESSAGE = "Cpf já cadastrado";

    private static Integer STATUS = 500;

    public CustomerExistsCpfException() {
        super(MESSAGE);
    }

    public int getStatus() {
        return STATUS;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
