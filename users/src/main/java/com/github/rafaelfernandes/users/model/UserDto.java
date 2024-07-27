package com.github.rafaelfernandes.users.model;

import java.util.UUID;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.enums.UserRoles;

public record UserDto(
    UUID id,
    String name,
    String email,
    UserRoles role
) {

    public static UserDto fromEntity(UserEntity entity){
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail(), entity.getUserRoles());
    }

} 
