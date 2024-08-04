package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {

    public CreditCardJpaEntity toJpaEntity(CreditCard creditCard) {
        return CreditCardJpaEntity.builder()
                .document(creditCard.getCpf())
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
                creditCardJpaEntity.getNumber(),
                creditCardJpaEntity.getExpirationDate(),
                creditCardJpaEntity.getCvv(),
                creditCardJpaEntity.getLimitValue()
        );
    }
}
