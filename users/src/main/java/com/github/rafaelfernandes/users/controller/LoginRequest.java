package com.github.rafaelfernandes.users.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull(message = "Username cannot be null")
        String username,

        @NotNull(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {

}
