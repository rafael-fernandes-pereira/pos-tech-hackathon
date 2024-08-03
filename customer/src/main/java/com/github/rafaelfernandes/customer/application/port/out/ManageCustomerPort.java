package com.github.rafaelfernandes.customer.application.port.out;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;

import java.util.Optional;

public interface ManageCustomerPort {

    Boolean existsByEmail(String email);

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findById(Customer.CustomerId customerId);

    Customer save(Customer customer);
}
