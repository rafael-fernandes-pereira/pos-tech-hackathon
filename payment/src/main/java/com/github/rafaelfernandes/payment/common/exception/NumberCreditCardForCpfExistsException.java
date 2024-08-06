package com.github.rafaelfernandes.payment.common.exception;

public class NumberCreditCardForCpfExistsException extends RuntimeException{

    private static final String MESSAGE = "Cartão de crédito já cadastrado para o CPF informado";

    private static Integer STATUS = 500;

    public NumberCreditCardForCpfExistsException() {
        super(MESSAGE);
    }

    public int getStatus() {
        return STATUS;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
