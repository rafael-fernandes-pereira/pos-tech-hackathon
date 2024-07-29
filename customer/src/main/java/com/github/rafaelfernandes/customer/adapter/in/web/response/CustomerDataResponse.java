package com.github.rafaelfernandes.customer.adapter.in.web.response;

import com.github.rafaelfernandes.customer.common.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;

public record CustomerDataResponse(

        @Schema(minimum = "11", maximum = "14")
        String cpf,

        @Schema(minimum = "3")
        String nome,

        @Schema(nullable = false)
        String email,

        @Schema(minimum = "11", maximum = "11")
        String telefone,

        @Schema(minimum = "10", maximum = "150")
        String rua,

        @Schema(minimum = "3", maximum = "60")
        String cidade,

        @Schema(implementation = State.class)
        String estado,

        @Schema(minimum = "9", maximum = "9")
        String cep,

        @Schema(minimum = "3", maximum = "60")
        String pais
) {
}
