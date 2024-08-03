package com.github.rafaelfernandes.creditcard.port.in;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;

public interface ManageCreditCardUseCase {

    CreditCard.CreditCardId create(CreditCard customer);

    CreditCard findByCreditCard(String cpf, String number);

}
