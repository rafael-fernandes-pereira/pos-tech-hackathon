package com.github.rafaelfernandes.payment.common.exception;

public class NumberCreditCardByCpfException extends RuntimeException{

    private static final String MESSAGE = "Quantidade de cartões de crédito excedida";

    private static Integer STATUS = 403;

    public NumberCreditCardByCpfException() {
        super(MESSAGE);
    }

    public int getStatus() {
        return STATUS;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
