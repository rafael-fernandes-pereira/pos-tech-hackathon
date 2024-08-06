package com.github.rafaelfernandes.creditcard.application.service;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import com.github.rafaelfernandes.creditcard.common.annotations.UseCase;
import com.github.rafaelfernandes.creditcard.common.exception.*;
import com.github.rafaelfernandes.creditcard.port.in.ManageCreditCardUseCase;
import com.github.rafaelfernandes.creditcard.port.out.CustomerPort;
import com.github.rafaelfernandes.creditcard.port.out.ManageCreditCardPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class ManageCreditCardService implements ManageCreditCardUseCase {

    private final ManageCreditCardPort manageCreditCardPort;

    private final CustomerPort customerPort;

    @Override
    public CreditCard.CreditCardId create(CreditCard creditCard) {

        var customerId = customerPort.findIdByCpf(creditCard.getCpf());

        if (customerId.isEmpty()) throw new CustomerNotFoundException();

        var creditCardExists = manageCreditCardPort.existsByCpfAndNumber(creditCard.getCpf(), creditCard.getNumero());

        if (Boolean.TRUE.equals(creditCardExists)) throw new NumberCreditCardForCpfExistsException();

        var size = manageCreditCardPort.sizeByCPF(creditCard.getCpf());

        if (size == 2) throw new NumberCreditCardByCpfException();

        var created = manageCreditCardPort.save(creditCard, customerId.get());

        return created.getCreditCardId();
    }

    @Override
    public CreditCard findCreditCardByCpfAndNumber(String cpf, String number) {

        var creditCard = manageCreditCardPort.findCreditCardByCpfAndNumber(cpf, number);

        if (creditCard.isEmpty()) throw new CreditCardNotFoundException();

        return creditCard.get();
    }

    @Override
    public Boolean updateLimit(String cpf, String number, BigDecimal limit) {

        var creditCard = findCreditCardByCpfAndNumber(cpf, number);

        var newLimit = creditCard.getLimite().subtract(limit);

        if (newLimit.compareTo(BigDecimal.ZERO) < 0) throw new CreditCardLimiteExceededFoundException();

        var updated = manageCreditCardPort.updateLimit(cpf, number, newLimit);

        return updated.isPresent();

    }
}
