package com.github.rafaelfernandes.users.service;

import java.util.UUID;

import com.github.rafaelfernandes.users.controller.UserDataResponse;
import com.github.rafaelfernandes.users.controller.UserIdResponse;
import com.github.rafaelfernandes.users.validation.ValidationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.users.controller.UserRegisterRequest;
import com.github.rafaelfernandes.users.exception.UserException;
import com.github.rafaelfernandes.users.repository.UserRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRespository respository;
    private final ValidationRequest validationRequest;


    public UserIdResponse create(UserRegisterRequest request){

        var errors = validationRequest.cliente(request);

        if (!errors.isEmpty()){
            throw new UserException(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    errors.toString()
            );
        }

        respository.findFirstByEmailOrDocument(request.email(), request.cpf())
                .ifPresent(user -> {
                    throw new UserException(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Email ou CPF já cadastrado"
                    );
                });

        var user = request.toEntity();
        
        user.setId(UUID.randomUUID());

        user.getContact().setId(UUID.randomUUID());

        final var created = respository.save(user);

        return UserIdResponse.fromEntity(created.getId());

    }


    public UserDataResponse findById(String id) {
        return respository.findById(UUID.fromString(id))
                .map(UserDataResponse::fromEntity)
                .orElseThrow(() -> new UserException(
                        HttpStatus.NOT_FOUND.value(),
                        "Usuário não encontrado"
                ));
    }
}
