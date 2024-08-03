package com.github.rafaelfernandes.creditcard.adapter.in.web.request;

import java.math.BigInteger;

public record CreditCardRequest(
        String cpf,
        String numero,
        String data_validade,
        String cvv,
        BigInteger limite
) {
}
