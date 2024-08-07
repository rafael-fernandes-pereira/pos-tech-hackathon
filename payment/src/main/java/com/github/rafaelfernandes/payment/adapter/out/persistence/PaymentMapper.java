package com.github.rafaelfernandes.payment.adapter.out.persistence;

import com.github.rafaelfernandes.payment.application.domain.model.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMapper {

    PaymentJpaEntity fromDomain(Payment payment) {

        var paymentJpaEntity = new PaymentJpaEntity();
        paymentJpaEntity.setCustomerId(UUID.fromString(payment.getCustomerId()));
        paymentJpaEntity.setPaymentMethod(payment.getPaymentMethod());
        paymentJpaEntity.setStatus(payment.getStatus());
        paymentJpaEntity.setDescription(payment.getDescription());
        paymentJpaEntity.setCreditCardId(UUID.fromString(payment.getCreditCardId()));
        paymentJpaEntity.setValue(payment.getValue());
        paymentJpaEntity.setDocument(payment.getCpf());

        return paymentJpaEntity;
    }

    Payment toDomain(PaymentJpaEntity paymentJpaEntity) {

        return Payment.of(
                paymentJpaEntity.getId(),
                paymentJpaEntity.getCustomerId(),
                paymentJpaEntity.getCreditCardId(),
                paymentJpaEntity.getPaymentMethod(),
                paymentJpaEntity.getDescription(),
                paymentJpaEntity.getValue(),
                paymentJpaEntity.getStatus(),
                paymentJpaEntity.getDocument()
        );

    }

}
