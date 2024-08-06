package com.github.rafaelfernandes.payment.application.port.out;

import com.github.rafaelfernandes.payment.application.domain.model.CreditCard;

import java.math.BigDecimal;
import java.util.Optional;

public interface CreditCardPort {

    Optional<CreditCard> findByCpfAndNumber(String cpf, String number);

    void updateLimit(CreditCard creditCard, BigDecimal value);

}
