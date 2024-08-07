package com.github.rafaelfernandes.payment.application.port.out;

import com.github.rafaelfernandes.payment.application.domain.model.Payment;

import java.util.List;
import java.util.UUID;

public interface ManagePaymentPort {

    Payment create(Payment payment);

    List<Payment> findByCpf(String cpf);

}
