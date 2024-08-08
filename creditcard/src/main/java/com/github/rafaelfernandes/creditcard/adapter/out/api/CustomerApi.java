package com.github.rafaelfernandes.creditcard.adapter.out.api;

import com.github.rafaelfernandes.creditcard.common.annotations.ApiAdapter;
import com.github.rafaelfernandes.creditcard.port.out.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@ApiAdapter
@RequiredArgsConstructor
public class CustomerApi implements CustomerPort {

    private final RestTemplate restTemplate;

    @Value("${customer.api}")
    private String customerApi;

    @Override
    public Optional<UUID> findIdByCpf(String cpf) {

        var response = restTemplate.getForEntity(customerApi + "/api/cliente?cpf={cpf}", CustomerDataResponse.class, cpf);

        return Optional.ofNullable(response.getBody())
                .map(CustomerDataResponse::id);



    }
}
