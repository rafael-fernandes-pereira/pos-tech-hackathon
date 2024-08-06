package com.github.rafaelfernandes.creditcard.adapter.in.web;

import com.github.rafaelfernandes.creditcard.adapter.in.web.request.CreditCardRequest;
import com.github.rafaelfernandes.creditcard.adapter.in.web.request.CreditCardUpdateLimitRequest;
import com.github.rafaelfernandes.creditcard.adapter.in.web.response.CreditCardDataResponse;
import com.github.rafaelfernandes.creditcard.adapter.in.web.response.CreditCardIdResponse;
import com.github.rafaelfernandes.creditcard.application.model.CreditCard;
import com.github.rafaelfernandes.creditcard.common.annotations.WebAdapter;
import com.github.rafaelfernandes.creditcard.common.exception.ResponseError;
import com.github.rafaelfernandes.creditcard.port.in.ManageCreditCardUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/cartao")
@AllArgsConstructor
@Tag(name = "02 - Credit Card", description = "Credit Card Endpoint")
public class CreditCardController {

    private final ManageCreditCardUseCase useCase;

    @Operation(summary = "Create a Credit Card for a Customer")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CreditCardIdResponse.class)
            )),
            @ApiResponse(description = "Number max of credit card exceded", responseCode = "403", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseError.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"Business and Internal problems\",\"status\":500}")
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
            path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreditCardIdResponse> register(@Parameter @RequestBody CreditCardRequest request){

        var creditCardModel = new CreditCard(
                request.cpf(),
                request.numero(),
                request.data_validade(),
                request.cvv(),

                request.limite()
        );

        var newCreditCard = useCase.create(creditCardModel);

        var response = new CreditCardIdResponse(UUID.fromString(newCreditCard.id()));

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @Operation(summary = "Get a Credit Card by Cpf And Number")
    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreditCardDataResponse> getCreditCard(@RequestParam("cpf") String cpf, @RequestParam("numero") String numero){

        var creditCard = useCase.findCreditCardByCpfAndNumber(cpf, numero);

        var response = new CreditCardDataResponse(
                UUID.fromString(creditCard.getCreditCardId().id()),
                creditCard.getCpf(),
                creditCard.getCustomerId(),
                creditCard.getNumero(),
                creditCard.getDataValidade(),
                creditCard.getCodigoSeguranca(),
                creditCard.getLimite()
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PutMapping(
            path = "/limite",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Boolean> updateLimit(@RequestBody CreditCardUpdateLimitRequest request){

        var updated = useCase.updateLimit(request.cpf(), request.numero(), request.compra());

        return ResponseEntity.status(HttpStatus.OK.value()).body(updated);
    }

}
