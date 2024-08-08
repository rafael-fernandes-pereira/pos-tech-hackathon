package com.github.rafaelfernandes.payment.adapter.out.api;

import com.github.rafaelfernandes.payment.application.domain.model.CreditCard;
import com.github.rafaelfernandes.payment.application.port.out.CreditCardPort;
import com.github.rafaelfernandes.payment.common.annotations.ApiAdapter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@ApiAdapter
@RequiredArgsConstructor
public class CreditCardApi implements CreditCardPort {

    @Value("${creditcard.api}")
    private String creditCardApi;

    private final RestTemplate restTemplate;

    @Override
    public Optional<CreditCard> findByCpfAndNumber(String cpf, String number) {
        var response = restTemplate.getForEntity(creditCardApi + "/cartao?cpf={cpf}&numero={numero}", CreditCardDataResponse.class, cpf, number);

        if (response.getStatusCode().is4xxClientError()) return Optional.empty();

        var creditCard = CreditCard.of(
                response.getBody().id().toString(),
                response.getBody().cpf(),
                response.getBody().customerId(),
                response.getBody().numero(),
                response.getBody().data_validade(),
                response.getBody().cvv(),
                response.getBody().limite()
        );

        return Optional.of(creditCard);
    }

    @Override
    public void updateLimit(CreditCard creditCard, BigDecimal value) {

        var request = new CreditCardUpdateLimitRequest(creditCard.getCpf(), creditCard.getNumero(), value);

        restTemplate.put(creditCardApi+ "/cartao/limite", request);

    }
}
