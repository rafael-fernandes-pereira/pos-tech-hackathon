package com.github.rafaelfernandes.payment.application.port.in;

import com.github.rafaelfernandes.payment.application.domain.model.CreditCard;
import com.github.rafaelfernandes.payment.application.domain.model.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ManagePaymentUseCase {

    Payment create(CreditCard creditCard, BigDecimal value, String description);

    List<Payment> findByCustomerId(UUID customerId);

}
