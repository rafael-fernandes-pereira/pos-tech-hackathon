package com.github.rafaelfernandes.creditcard.port.out;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ManageCreditCardPort {
    Integer sizeByCPF(String cpf);

    Boolean existsByCpfAndNumber(String cpf, String number);

    CreditCard save(CreditCard creditCard, UUID customerId);

    Optional<CreditCard> findCreditCardByCpfAndNumber(String cpf, String number);

    Optional<CreditCard> updateLimit(String cpf, String number, BigDecimal limit);

}
