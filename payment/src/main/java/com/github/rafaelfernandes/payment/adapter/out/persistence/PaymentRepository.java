package com.github.rafaelfernandes.payment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, UUID> {

}
