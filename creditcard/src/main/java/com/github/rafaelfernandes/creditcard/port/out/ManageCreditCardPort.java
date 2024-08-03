package com.github.rafaelfernandes.creditcard.port.out;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;

public interface ManageCreditCardPort {
    Integer sizeByCPF(String cpf);

    Boolean existsByCpfAndNumber(String cpf, String number);

    CreditCard save(CreditCard creditCard);
}
