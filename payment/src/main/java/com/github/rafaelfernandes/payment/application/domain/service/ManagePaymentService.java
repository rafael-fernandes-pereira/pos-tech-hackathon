package com.github.rafaelfernandes.payment.application.domain.service;

import com.github.rafaelfernandes.payment.application.domain.model.CreditCard;
import com.github.rafaelfernandes.payment.application.domain.model.Payment;
import com.github.rafaelfernandes.payment.application.port.in.ManagePaymentUseCase;

import com.github.rafaelfernandes.payment.application.port.out.CreditCardPort;
import com.github.rafaelfernandes.payment.application.port.out.ManagePaymentPort;
import com.github.rafaelfernandes.payment.common.annotations.UseCase;
import com.github.rafaelfernandes.payment.common.enums.Status;
import com.github.rafaelfernandes.payment.common.exception.CreditCardLimiteExceededFoundException;
import com.github.rafaelfernandes.payment.common.exception.CreditCardNotFoundException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@UseCase
@AllArgsConstructor
public class ManagePaymentService implements ManagePaymentUseCase {

    private final CreditCardPort creditCardPort;
    private final ManagePaymentPort managePaymentPort;

    @Override
    public Payment create(CreditCard creditCard, BigDecimal value, String description) {

        var creditCardFound = creditCardPort.findByCpfAndNumber(creditCard.getCpf(), creditCard.getNumero());

        if (creditCardFound.isEmpty()) throw new CreditCardNotFoundException();

        if (!creditCardFound.get().getCodigoSeguranca().equals(creditCard.getCodigoSeguranca()))  throw new CreditCardNotFoundException();

        if (!creditCardFound.get().getDataValidade().equals(creditCard.getDataValidade()))  throw new CreditCardNotFoundException();

        if (creditCardFound.get().getLimite().compareTo(value) < 0) throw new CreditCardLimiteExceededFoundException();

        var payment = Payment.from(creditCardFound.get(), description, value, Status.APROVADO);

        var paymentCreated = managePaymentPort.create(payment);

        creditCardPort.updateLimit(creditCardFound.get(), value);

        return paymentCreated;

    }

    @Override
    public List<Payment> findByCpf(String cpf) {
        return managePaymentPort.findByCpf(cpf);
    }
}
