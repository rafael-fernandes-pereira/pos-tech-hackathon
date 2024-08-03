package com.github.rafaelfernandes.creditcard.adapter.out.api;

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
