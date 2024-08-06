package com.github.rafaelfernandes.payment.adapter.in.web;

import com.github.rafaelfernandes.payment.adapter.in.web.request.PaymentRequest;
import com.github.rafaelfernandes.payment.adapter.in.web.response.PaymentIdResponse;
import com.github.rafaelfernandes.payment.application.domain.model.CreditCard;
import com.github.rafaelfernandes.payment.application.port.in.ManagePaymentUseCase;
import com.github.rafaelfernandes.payment.common.annotations.WebAdapter;
import com.github.rafaelfernandes.payment.common.util.FakerData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
@Tag(name = "03 - Payment", description = "Payment Endpoint")
public class PaymentController {

    private final ManagePaymentUseCase useCase;

    @PostMapping(
            path = "/",
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

}
