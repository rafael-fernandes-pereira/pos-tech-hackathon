package com.github.rafaelfernandes.payment.adapter.in.web.response;

import java.math.BigDecimal;

public record PaymentDataResponse(

        BigDecimal valor,
        String descricao,
        String metodo_pagamento,
        String status

) {

    public PaymentDataResponse(BigDecimal valor, String descricao, String metodo_pagamento, String status) {
        this.valor = valor;
        this.descricao = descricao;
        this.metodo_pagamento = metodo_pagamento.toLowerCase();
        this.status = status.toLowerCase();
    }

}
