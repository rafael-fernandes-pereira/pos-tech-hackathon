package com.github.rafaelfernandes.users.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.users.controller.UserRegisterRequest;
import com.github.rafaelfernandes.users.exception.UserException;
import com.github.rafaelfernandes.users.model.UserDto;
import com.github.rafaelfernandes.users.repository.UserRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRespository respository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto create(UserRegisterRequest request){

        respository.findFirstByEmail(request.email())
                .ifPresent(user -> {
                    throw new UserException(
                            HttpStatus.BAD_REQUEST.value(),
                            "User with email " + request.email() + " already exists"
                    );
                });

        var user = request.toEntity();
        
        user.setPassword(bCryptPasswordEncoder.encode(request.password()));
        user.setId(UUID.randomUUID());
        final var created = respository.save(user);

        return UserDto.fromEntity(created);

    }
    

}
