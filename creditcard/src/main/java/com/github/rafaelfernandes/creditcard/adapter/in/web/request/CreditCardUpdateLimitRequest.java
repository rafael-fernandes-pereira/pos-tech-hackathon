package com.github.rafaelfernandes.creditcard.adapter.in.web.request;

import java.math.BigDecimal;

public record CreditCardUpdateLimitRequest(
        String cpf,
        String numero,
        BigDecimal compra
) {
}
