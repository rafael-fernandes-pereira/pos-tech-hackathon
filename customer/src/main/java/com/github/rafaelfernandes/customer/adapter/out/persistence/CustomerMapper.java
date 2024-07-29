package com.github.rafaelfernandes.customer.adapter.out.persistence;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    CustomerJpaEntity toCreateJpaEntity(Customer customer) {

        ContactJpaEntity contactJpaEntity = ContactJpaEntity.builder()
                .phone(customer.getContact().getTelefone())
                .zipcode(customer.getContact().getCep())
                .street(customer.getContact().getRua())
                .city(customer.getContact().getCidade())
                .state(customer.getContact().getEstado())
                .country(customer.getContact().getPais())
                .build();

        return CustomerJpaEntity.builder()
                .name(customer.getNome())
                .email(customer.getEmail())
                .document(customer.getCpf())
                .contact(contactJpaEntity)
                .build();
    }

    Customer toDomain(CustomerJpaEntity customerJpaEntity) {

        Customer.Contact contact = new Customer.Contact(
                customerJpaEntity.getContact().getPhone(),
                customerJpaEntity.getContact().getStreet(),
                customerJpaEntity.getContact().getCity(),
                customerJpaEntity.getContact().getState(),
                customerJpaEntity.getContact().getZipcode(),
                customerJpaEntity.getContact().getCountry()
        );

        return Customer.of(
                customerJpaEntity.getId().toString(),
                customerJpaEntity.getDocument(),
                customerJpaEntity.getName(),
                customerJpaEntity.getEmail(),

                contact
        );

    }

}
