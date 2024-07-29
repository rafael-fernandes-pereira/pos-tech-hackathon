package com.github.rafaelfernandes.customer.adapter.in.web.response;

public record CustomerDataResponse(
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
