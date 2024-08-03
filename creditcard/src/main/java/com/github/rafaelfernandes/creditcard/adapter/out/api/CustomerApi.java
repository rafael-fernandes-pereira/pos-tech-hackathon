package com.github.rafaelfernandes.creditcard.adapter.out.api;

import com.github.rafaelfernandes.creditcard.common.annotations.ApiAdapter;
import com.github.rafaelfernandes.creditcard.port.out.CustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@ApiAdapter
@AllArgsConstructor
public class CustomerApi implements CustomerPort {

    private final RestTemplate restTemplate;

    @Override
    public Boolean existsByCpf(String cpf) {

        var response = restTemplate.getForEntity("http://localhost:8081/cliente/?cpf={cpf}", CustomerDataResponse.class, cpf);

        return response.getStatusCode().is2xxSuccessful();

    }
}
