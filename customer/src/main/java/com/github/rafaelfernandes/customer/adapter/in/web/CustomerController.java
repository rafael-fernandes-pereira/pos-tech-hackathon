package com.github.rafaelfernandes.customer.adapter.in.web;

import com.github.rafaelfernandes.customer.application.domain.model.Customer;
import com.github.rafaelfernandes.customer.application.port.in.ManageCustomerUseCase;
import com.github.rafaelfernandes.customer.common.annotations.WebAdapter;
import com.github.rafaelfernandes.customer.adapter.in.web.response.CustomerDataResponse;
import com.github.rafaelfernandes.customer.adapter.in.web.response.CustomerIdResponse;
import com.github.rafaelfernandes.customer.adapter.in.web.request.CustomerRequest;
import com.github.rafaelfernandes.customer.common.response.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
@Tag(name = "01 - Customer", description = "Customer Endpoint")
public class CustomerController {

    private final ManageCustomerUseCase useCase;

    @Operation(summary = "Create a Customer")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomerIdResponse.class)
            )),
            @ApiResponse(description = "Business and Internal problems", responseCode = "500", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Business and Internal problems\",\"status\":500}")
            )),
            @ApiResponse(description = "Authenticate error", responseCode = "401", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Authenticate error\",\"status\":401}")
            ))
    })
    @PostMapping(
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerIdResponse> register(@Parameter @RequestBody CustomerRequest request){

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

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @Operation(summary = "Find a Customer by CPF")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomerDataResponse.class)
            )),
            @ApiResponse(description = "Business and Internal problems", responseCode = "500", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Business and Internal problems\",\"status\":500}")
            )),
            @ApiResponse(description = "Authenticate error", responseCode = "401", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Authenticate error\",\"status\":401}")
            ))
    })
    @GetMapping(
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDataResponse> findByCpf(@Parameter @RequestParam("cpf") String cpf){

        var customer = useCase.findByCpf(cpf);

        var response = new CustomerDataResponse(
                UUID.fromString(customer.getCustomerId().id()),
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

    @Operation(summary = "Find a Customer by Id")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomerDataResponse.class)
            )),
            @ApiResponse(description = "Business and Internal problems", responseCode = "500", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Business and Internal problems\",\"status\":500}")
            )),
            @ApiResponse(description = "Authenticate error", responseCode = "401", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Authenticate error\",\"status\":401}")
            ))
    })
    @GetMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDataResponse> findById(@Parameter @PathVariable String id){

        var customerIdModel = new Customer.CustomerId(id);

        var customer = useCase.findById(customerIdModel);

        var response = new CustomerDataResponse(
                UUID.fromString(customer.getCustomerId().id()),
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
