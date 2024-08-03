package com.github.rafaelfernandes.customer.adapter.out.persistence;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;
import com.github.rafaelfernandes.customer.application.port.out.ManageCustomerPort;
import com.github.rafaelfernandes.customer.common.annotations.PersistenceAdapter;
import com.github.rafaelfernandes.customer.common.exception.CustomerExistsCpfException;
import com.github.rafaelfernandes.customer.common.exception.CustomerExistsEmailException;
import com.github.rafaelfernandes.customer.common.exception.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements ManageCustomerPort {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public Customer save(Customer customer) {

        var customerJpaEntity = customerMapper.toCreateJpaEntity(customer);

        var saved = customerRepository.save(customerJpaEntity);

        return customerMapper.toDomain(saved);

    }

    @Override
    @Transactional
    public Optional<Customer> findById(Customer.CustomerId customerId) {

        var idUuid = UUID.fromString(customerId.id());

        var customerJpaEntity = customerRepository.findById(idUuid);

        if (customerJpaEntity.isEmpty()) return Optional.empty();

        var customer = customerMapper.toDomain(customerJpaEntity.get());

        return Optional.of(customer);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {

        var customer = customerRepository.findByDocument(cpf);

        if (customer.isEmpty()) return Optional.empty();

        return Optional.of(customerMapper.toDomain(customer.get()));

    }
}
