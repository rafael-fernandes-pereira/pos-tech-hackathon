package com.github.rafaelfernandes.customer.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CustomerIdResponse(

        @Schema(implementation = UUID.class)
        UUID id_cliente
) {}
