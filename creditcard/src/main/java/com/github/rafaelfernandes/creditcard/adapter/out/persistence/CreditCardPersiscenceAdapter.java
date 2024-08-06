package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import com.github.rafaelfernandes.creditcard.common.annotations.PersistenceAdapter;
import com.github.rafaelfernandes.creditcard.port.out.ManageCreditCardPort;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@AllArgsConstructor
public class CreditCardPersiscenceAdapter implements ManageCreditCardPort {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    @Override
    public Integer sizeByCPF(String cpf) {
        return creditCardRepository.countByDocument(cpf);
    }

    @Override
    public Boolean existsByCpfAndNumber(String cpf, String number) {
        return creditCardRepository.existsByDocumentAndNumber(cpf, number);
    }

    @Override
    public CreditCard save(CreditCard creditCard, UUID customerId) {

        var creditCardJpaEntity = creditCardMapper.toJpaEntity(creditCard, customerId);

        var saved = creditCardRepository.save(creditCardJpaEntity);

        return creditCardMapper.toDomain(saved);

    }

    @Override
    public Optional<CreditCard> findCreditCardByCpfAndNumber(String cpf, String number) {
        var creditCardJpaEntity = creditCardRepository.findByDocumentAndNumber(cpf, number);

        if (creditCardJpaEntity == null) return Optional.empty();

        return Optional.of(creditCardMapper.toDomain(creditCardJpaEntity));


    }

    @Override
    public Optional<CreditCard> updateLimit(String cpf, String number, BigDecimal limit) {

        var creditCardJpaEntity = creditCardRepository.findByDocumentAndNumber(cpf, number);

        if (creditCardJpaEntity == null) return Optional.empty();

        creditCardJpaEntity.setLimitValue(limit);

        var updated = creditCardRepository.save(creditCardJpaEntity);

        return Optional.of(creditCardMapper.toDomain(updated));

    }
}
