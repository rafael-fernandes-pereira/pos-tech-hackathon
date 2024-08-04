package com.github.rafaelfernandes.creditcard.port.in;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;

public interface ManageCreditCardUseCase {

    CreditCard.CreditCardId create(CreditCard customer);

    CreditCard findCreditCardByCpfAndNumber(String cpf, String number);

}
