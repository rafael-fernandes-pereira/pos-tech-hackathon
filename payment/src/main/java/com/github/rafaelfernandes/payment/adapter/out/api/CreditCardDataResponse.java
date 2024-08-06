package com.github.rafaelfernandes.payment.adapter.out.api;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditCardDataResponse(
        UUID id,
        String cpf,
        String numero,
        String data_validade,
        String cvv,
        BigDecimal limite
) {
}
