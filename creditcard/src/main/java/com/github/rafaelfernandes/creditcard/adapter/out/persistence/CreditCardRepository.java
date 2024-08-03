package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCardJpaEntity, UUID> {
    Integer countByCpf(String cpf);

    Boolean existsByCpfAndNumber(String cpf, String number);
}
