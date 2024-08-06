package com.github.rafaelfernandes.creditcard.adapter.out.api;

import com.github.rafaelfernandes.creditcard.common.annotations.ApiAdapter;
import com.github.rafaelfernandes.creditcard.port.out.CustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@ApiAdapter
@AllArgsConstructor
public class CustomerApi implements CustomerPort {

    private final RestTemplate restTemplate;

    @Override
    public Optional<UUID> findIdByCpf(String cpf) {

        var response = restTemplate.getForEntity("http://localhost:8081/cliente/?cpf={cpf}", CustomerDataResponse.class, cpf);

        return Optional.ofNullable(response.getBody())
                .map(CustomerDataResponse::id);



    }
}
