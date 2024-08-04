package com.github.rafaelfernandes.creditcard.application.service;

import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import com.github.rafaelfernandes.creditcard.common.annotations.UseCase;
import com.github.rafaelfernandes.creditcard.common.exception.CustomerNotFoundException;
import com.github.rafaelfernandes.creditcard.common.exception.NumberCreditCardByCpfException;
import com.github.rafaelfernandes.creditcard.common.exception.NumberCreditCardForCpfExistsException;
import com.github.rafaelfernandes.creditcard.port.in.ManageCreditCardUseCase;
import com.github.rafaelfernandes.creditcard.port.out.CustomerPort;
import com.github.rafaelfernandes.creditcard.port.out.ManageCreditCardPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ManageCreditCardService implements ManageCreditCardUseCase {

    private final ManageCreditCardPort manageCreditCardPort;

    private final CustomerPort customerPort;

    @Override
    public CreditCard.CreditCardId create(CreditCard customer) {

        var customerExists = customerPort.existsByCpf(customer.getCpf());

        if (customerExists == Boolean.FALSE) throw new CustomerNotFoundException();

        var creditCardExists = manageCreditCardPort.existsByCpfAndNumber(customer.getCpf(), customer.getNumero());

        if (Boolean.TRUE.equals(creditCardExists)) throw new NumberCreditCardForCpfExistsException();

        var size = manageCreditCardPort.sizeByCPF(customer.getCpf());

        if (size == 2) throw new NumberCreditCardByCpfException();

        var created = manageCreditCardPort.save(customer);

        return created.getCreditCardId();
    }

    @Override
    public CreditCard findCreditCardByCpfAndNumber(String cpf, String number) {

        var creditCard = manageCreditCardPort.findCreditCardByCpfAndNumber(cpf, number);

        if (creditCard.isEmpty()) throw new CustomerNotFoundException();

        return creditCard.get();


    }
}
