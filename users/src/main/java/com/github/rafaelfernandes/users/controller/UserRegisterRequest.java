package com.github.rafaelfernandes.users.controller;

import java.util.Objects;

import com.github.rafaelfernandes.users.entity.ContactEntity;
import com.github.rafaelfernandes.users.enums.State;
import jakarta.validation.constraints.*;

import com.github.rafaelfernandes.users.entity.UserEntity;


public record UserRegisterRequest(

    @NotNull(message = "CPF deve ser preenchido")
    @Size(min = 11, max = 11, message = "CPF deve conter 11 caracteres")
    String cpf,

    @NotNull(message = "Nome deve ser preenchido")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    String nome,
    
    @NotNull(message = "Email deve ser preenchido")
    @Email(message = "Email deve ser válido")
    String email,

    @NotNull(message = "Telefone deve ser preenchido")
    @Size(min = 11, max = 11, message = "Telefone deve conter 11 caracteres")
    String telefone,

    @NotEmpty(message = "Rua deve ser preenchida")
    @Size( min = 10, max = 150, message = "Rua deve ter no máximo 150 caracteres")
    String rua,

    @NotEmpty(message = "Cidade deve ser preenchida")
    @Size( min = 3, max = 60, message = "Cidade deve ter no minimo 3 e no máximo 60 caracteres")
    String cidade,

    @NotNull(message = "Estado deve ser preenchido")
    State estado,

    @NotNull(message = "CEP deve ser preenchido")
    @Size(min = 9, max = 9, message = "CEP deve conter 8 caracteres")
    String cep,

    @NotNull(message = "País deve ser preenchido")
    @Size(min = 3, max = 60, message = "País deve ter no minimo 3 e no máximo 60 caracteres")
    String pais

) {

    public UserEntity toEntity(){

        ContactEntity contactEntity = ContactEntity.builder()
                .phone(telefone)
                .street(rua)
                .city(cidade)
                .state(estado)
                .zipcode(cep)
                .country(pais)
            .build();

        return UserEntity.builder()
            .name(nome)
            .email(email)
            .document(cpf)
            .contact(contactEntity)
            .build();
    }

}
