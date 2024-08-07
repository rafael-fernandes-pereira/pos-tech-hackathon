package com.github.rafaelfernandes.creditcard.adapter.in.web.request;

import java.math.BigDecimal;
import java.math.BigInteger;

public record CreditCardRequest(
        String cpf,
        String numero,
        String data_validade,
        String cvv,
        BigDecimal limite
) {
}
