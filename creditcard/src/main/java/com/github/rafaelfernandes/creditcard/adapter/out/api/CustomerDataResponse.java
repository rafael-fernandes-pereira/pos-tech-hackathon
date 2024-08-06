package com.github.rafaelfernandes.creditcard.adapter.out.api;

import java.util.UUID;

public record CustomerDataResponse(

        UUID id,

        String cpf,

        String nome,

        String email,

        String telefone,

        String rua,

        String cidade,

        String estado,

        String cep,

        String pais
) {
}
