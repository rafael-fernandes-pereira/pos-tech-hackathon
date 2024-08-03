package com.github.rafaelfernandes.customer.application.domain.service;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;
import com.github.rafaelfernandes.customer.application.port.in.ManageCustomerUseCase;
import com.github.rafaelfernandes.customer.application.port.out.ManageCustomerPort;
import com.github.rafaelfernandes.customer.common.annotations.UseCase;
import com.github.rafaelfernandes.customer.common.exception.CustomerExistsCpfException;
import com.github.rafaelfernandes.customer.common.exception.CustomerExistsEmailException;
import com.github.rafaelfernandes.customer.common.exception.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ManageCustomerService implements ManageCustomerUseCase {

    private final ManageCustomerPort manageCustomerPort;

    @Override
    @Transactional
    public Customer.CustomerId createCustomer(Customer customer) {

        if (manageCustomerPort.findByCpf(customer.getCpf()).isPresent()) throw new CustomerExistsCpfException();

        if (Boolean.TRUE.equals(manageCustomerPort.existsByEmail(customer.getEmail()))) throw new CustomerExistsEmailException();

        Customer saved = manageCustomerPort.save(customer);

        return saved.getCustomerId();
    }

    @Override
    @Transactional
    public Customer findById(Customer.CustomerId customerId) {

        return manageCustomerPort.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer findByCpf(String cpf) {
        return manageCustomerPort.findByCpf(cpf)
                .orElseThrow(CustomerNotFoundException::new);

    }
}
