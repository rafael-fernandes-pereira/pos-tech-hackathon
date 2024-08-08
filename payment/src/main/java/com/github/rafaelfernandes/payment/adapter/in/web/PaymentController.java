package com.github.rafaelfernandes.payment.adapter.in.web;

import com.github.rafaelfernandes.payment.adapter.in.web.request.PaymentRequest;
import com.github.rafaelfernandes.payment.adapter.in.web.response.PaymentDataResponse;
import com.github.rafaelfernandes.payment.adapter.in.web.response.PaymentIdResponse;
import com.github.rafaelfernandes.payment.application.domain.model.CreditCard;
import com.github.rafaelfernandes.payment.application.port.in.ManagePaymentUseCase;
import com.github.rafaelfernandes.payment.common.annotations.WebAdapter;
import com.github.rafaelfernandes.payment.common.exception.ResponseError;
import com.github.rafaelfernandes.payment.common.util.FakerData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/pagamentos")
@AllArgsConstructor
@Tag(name = "03 - Payment", description = "Payment Endpoint")
public class PaymentController {

    private final ManagePaymentUseCase useCase;

    @Operation(summary = "Create a payment")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PaymentIdResponse.class)
            )),
            @ApiResponse(description = "Credit card limit excedeed", responseCode = "402", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Credit card limit excedeed\",\"status\":402}")
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
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentIdResponse> create(@RequestBody PaymentRequest paymentRequest) {

        var creditCard = new CreditCard(
                paymentRequest.cpf(),
                paymentRequest.numero(),
                paymentRequest.data_validade(),
                paymentRequest.cvv()
        );

        var payment = useCase.create(creditCard, paymentRequest.valor(), "Compra de produto " + FakerData.product());

        return ResponseEntity.ok(new PaymentIdResponse(payment.getPaymentId().id()));

    }

    @Operation(summary = "Get all payments by customer id")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PaymentDataResponse.class)
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
    @GetMapping(path = "/cliente/{cpf}")
    public ResponseEntity<List<PaymentDataResponse>> findByCustomerId(@PathVariable String cpf) {

        var payments = useCase.findByCpf(cpf);

        var response = payments.stream()
                .map(payment -> new PaymentDataResponse(
                        payment.getValue(),
                        payment.getDescription(),
                        payment.getPaymentMethod(),
                        payment.getStatus()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

}
