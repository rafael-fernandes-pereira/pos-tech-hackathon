package com.github.rafaelfernandes.users.controller;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.enums.State;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDataResponse(
        String cpf,

        String nome,

        String email,

        String telefone,

        String rua,

        String cidade,

        State estado,

        String cep,

        String pais
) {
    public static UserDataResponse fromEntity(UserEntity entity) {
        return new UserDataResponse(
                entity.getDocument(),
                entity.getName(),
                entity.getEmail(),
                entity.getContact().getPhone(),
                entity.getContact().getStreet(),
                entity.getContact().getCity(),
                entity.getContact().getState(),
                entity.getContact().getZipcode(),
                entity.getContact().getCountry()
        );
    }
}
