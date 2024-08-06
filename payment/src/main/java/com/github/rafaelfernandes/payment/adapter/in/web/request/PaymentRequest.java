package com.github.rafaelfernandes.payment.adapter.in.web.request;

import java.math.BigDecimal;

public record PaymentRequest(

        String cpf,
        String numero,
        String data_validade,
        String cvv,
        BigDecimal valor

) {
}
