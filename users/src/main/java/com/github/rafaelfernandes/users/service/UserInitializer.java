package com.github.rafaelfernandes.users.service;

import java.util.UUID;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.enums.UserRoles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import com.github.rafaelfernandes.users.repository.UserRespository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserInitializer implements CommandLineRunner {

    private final UserRespository respository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        var exists = respository.findFirstByUsername("adj2");

        if (exists.isPresent()){
            return;
        }

        var user = new UserEntity();
        user.setUsername("adj2");
        user.setPassword(bCryptPasswordEncoder.encode("adj@1234"));
        user.setId(UUID.randomUUID());
        user.setUserRoles(UserRoles.CUSTOMER);
        respository.save(user);


    }
}
