package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreditCardMapper {

    public CreditCardJpaEntity toJpaEntity(CreditCard creditCard, UUID customerId) {
        return CreditCardJpaEntity.builder()
                .document(creditCard.getCpf())
                .customerId(customerId)
                .expirationDate(creditCard.getDataValidade())
                .number(creditCard.getNumero())
                .cvv(creditCard.getCodigoSeguranca())
                .limitValue(creditCard.getLimite())
                .build();
    }

    public CreditCard toDomain(CreditCardJpaEntity creditCardJpaEntity) {
        return CreditCard.of(
                creditCardJpaEntity.getId().toString(),
                creditCardJpaEntity.getDocument(),
                creditCardJpaEntity.getCustomerId(),
                creditCardJpaEntity.getNumber(),
                creditCardJpaEntity.getExpirationDate(),
                creditCardJpaEntity.getCvv(),
                creditCardJpaEntity.getLimitValue()
        );
    }
}
