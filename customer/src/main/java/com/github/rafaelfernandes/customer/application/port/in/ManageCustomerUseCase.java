package com.github.rafaelfernandes.customer.application.port.in;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;

public interface ManageCustomerUseCase {

    Customer.CustomerId createCustomer(Customer customer);

    Customer findById(Customer.CustomerId customerId);

    Customer findByCpf(String cpf);

}
