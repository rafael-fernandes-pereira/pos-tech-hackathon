package com.github.rafaelfernandes.creditcard.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CreditCardIdResponse(

        @Schema(implementation = UUID.class)
        UUID id_cartao_credito
) {}
