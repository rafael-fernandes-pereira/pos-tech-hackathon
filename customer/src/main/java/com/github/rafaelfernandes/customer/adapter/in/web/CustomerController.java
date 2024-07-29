package com.github.rafaelfernandes.customer.adapter.in.web;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;
import com.github.rafaelfernandes.customer.application.port.in.ManageCustomerUseCase;
import com.github.rafaelfernandes.customer.common.annotations.WebAdapter;
import com.github.rafaelfernandes.customer.adapter.in.web.response.CustomerDataResponse;
import com.github.rafaelfernandes.customer.adapter.in.web.response.CustomerIdResponse;
import com.github.rafaelfernandes.customer.adapter.in.web.request.CustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class CustomerController {

    private final ManageCustomerUseCase useCase;

    @PostMapping(
            path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerIdResponse> register(@RequestBody CustomerRequest request){

        var contactModel = new Customer.Contact(
                request.telefone(),
                request.rua(),
                request.cidade(),
                request.estado(),
                request.cep(),
                request.pais()
        );

        var customerModel = new Customer(
                request.cpf(),
                request.nome(),
                request.email(),
                contactModel
        );

        var newUser = useCase.createCustomer(customerModel);

        var response = new CustomerIdResponse(UUID.fromString(newUser.id()));

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @GetMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDataResponse> findById(@PathVariable String id){

        var customerIdModel = new Customer.CustomerId(id);

        var customer = useCase.findById(customerIdModel);

        var response = new CustomerDataResponse(
                customer.getCpf(),
                customer.getNome(),
                customer.getEmail(),
                customer.getContact().getTelefone(),
                customer.getContact().getRua(),
                customer.getContact().getCidade(),
                customer.getContact().getEstado(),
                customer.getContact().getCep(),
                customer.getContact().getPais()
        );

        return ResponseEntity.ok(response);
    }

}
