package com.github.rafaelfernandes.users.model;

import java.util.UUID;

public record AuthenticateUser(
    UUID id,
    String email,
    String token
) {

}
