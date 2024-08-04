package com.github.rafaelfernandes.creditcard.adapter.in.web.response;

import java.math.BigInteger;

public record CreditCardDataResponse(
        String cpf,
        String numero,
        String data_validade,
        String cvv,
        BigInteger limite
) {
}
