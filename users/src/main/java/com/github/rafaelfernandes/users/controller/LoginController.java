package com.github.rafaelfernandes.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import com.github.rafaelfernandes.users.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationService service;

    @PostMapping("/autenticacao")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request){

        var token = service.authenticate(request);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/validacao")
    public ResponseEntity<String> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        try{
            service.validate(token);
        } catch (Exception e){
            return ResponseEntity.status(401).body("Invalid token");
        }

        return ResponseEntity.ok("validated");
    }

}
