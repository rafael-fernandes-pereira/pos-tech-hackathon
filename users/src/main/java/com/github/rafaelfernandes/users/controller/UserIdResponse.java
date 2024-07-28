package com.github.rafaelfernandes.users.controller;

import java.util.UUID;

public record UserIdResponse(
        UUID id_cliente
) {

        public static UserIdResponse fromEntity(UUID id){
            return new UserIdResponse(id);
        }
}
