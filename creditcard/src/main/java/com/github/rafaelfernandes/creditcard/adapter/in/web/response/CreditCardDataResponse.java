package com.github.rafaelfernandes.creditcard.adapter.in.web.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record CreditCardDataResponse(
        UUID id,
        String cpf,
        UUID customerId,
        String numero,
        String data_validade,
        String cvv,
        BigDecimal limite
) {
}
