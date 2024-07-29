package com.github.rafaelfernandes.customer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> {

    Boolean existsByEmail(String email);

    Boolean existsByDocument(String document);
}
