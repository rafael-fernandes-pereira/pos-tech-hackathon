package com.github.rafaelfernandes.users.controller;

import java.util.Objects;

import com.github.rafaelfernandes.users.enums.State;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.User;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.enums.UserRoles;


public record UserRegisterRequest(

    @NotNull(message = "CPF deve ser preenchido")
    @Size(min = 11, max = 11, message = "CPF deve conter 11 caracteres")
    String cpf,

    @NotNull(message = "Nome deve ser preenchido")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    String nome,
    
    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    String email,
    
    @NotNull(message = "Password is required")
    @Size(min = 10, message = "Password must be at least 10 characters long")
    String senha,

    @NotEmpty(message = "O campo rua deve estar preenchido")
    @Size( min = 10, max = 150, message = "O campo rua deve ter no máximo 150 caracteres")
    String rua,

    @NotEmpty(message = "O campo address.city deve estar preenchido")
    @Length( min = 3, max = 60, message = "O campo address.city deve ter no máximo 60 caracteres")
    String city,

    @NotNull(message = "O campo address.state deve estar preenchido")
    State state
    
    UserRoles role

) {

    public UserEntity toEntity(){
        return UserEntity.builder()
            .name(name)
            .password(password)
            .email(email)
            .userRoles(Objects.nonNull(role) ? role : UserRoles.CUSTOMER)
            .build();
    }

}
