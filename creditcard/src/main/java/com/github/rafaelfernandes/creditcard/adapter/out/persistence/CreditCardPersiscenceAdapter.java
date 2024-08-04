package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import com.github.rafaelfernandes.creditcard.common.annotations.PersistenceAdapter;
import com.github.rafaelfernandes.creditcard.port.out.ManageCreditCardPort;
import lombok.AllArgsConstructor;

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
    public CreditCard save(CreditCard creditCard) {

        var creditCardJpaEntity = creditCardMapper.toJpaEntity(creditCard);

        var saved = creditCardRepository.save(creditCardJpaEntity);

        return creditCardMapper.toDomain(saved);

    }
}
