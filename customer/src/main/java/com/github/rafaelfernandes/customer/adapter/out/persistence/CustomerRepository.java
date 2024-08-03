package com.github.rafaelfernandes.customer.adapter.out.persistence;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> {

    Boolean existsByEmail(String email);

    Optional<CustomerJpaEntity> findByDocument(String document);
}
