package com.github.rafaelfernandes.payment.adapter.out.persistence;

import com.github.rafaelfernandes.payment.application.domain.model.Payment;
import com.github.rafaelfernandes.payment.application.port.out.ManagePaymentPort;
import com.github.rafaelfernandes.payment.common.annotations.PersistenceAdapter;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@AllArgsConstructor
public class PaymentPersiscenceAdapter implements ManagePaymentPort {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment create(Payment payment) {

        var paymentJpaEntity = paymentMapper.fromDomain(payment);

        var saved = paymentRepository.save(paymentJpaEntity);

        return paymentMapper.toDomain(saved);

    }

    @Override
    public List<Payment> findByCustomerId(UUID customerId) {
        return Collections.emptyList();
    }
}
