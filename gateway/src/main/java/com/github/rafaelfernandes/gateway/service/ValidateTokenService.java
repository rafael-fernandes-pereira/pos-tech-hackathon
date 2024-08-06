package com.github.rafaelfernandes.gateway.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ValidateTokenService {

    private final RestTemplate restTemplate;

    @Value("${login.api}")
    private String loginApi;

    public boolean validate(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(token, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                loginApi + "/validate",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getStatusCode().is2xxSuccessful();
    }

}
