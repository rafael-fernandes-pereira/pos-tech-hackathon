package com.github.rafaelfernandes.payment.adapter.out.api;

import java.math.BigDecimal;

public record CreditCardUpdateLimitRequest(
        String cpf,
        String numero,
        BigDecimal compra
) {
}
