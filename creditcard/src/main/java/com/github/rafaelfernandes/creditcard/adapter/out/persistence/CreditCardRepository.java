package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCardJpaEntity, UUID> {
    Integer countByDocument(String document);

    Boolean existsByDocumentAndNumber(String cpf, String number);
}
