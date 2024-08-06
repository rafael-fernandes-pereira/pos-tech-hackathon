package com.github.rafaelfernandes.payment.application.domain.model;

import com.github.rafaelfernandes.payment.common.enums.PaymentMethod;
import com.github.rafaelfernandes.payment.common.enums.Status;
import com.github.rafaelfernandes.payment.common.validation.ValueOfEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

import static com.github.rafaelfernandes.payment.common.validation.Validation.validate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {

    private final PaymentId paymentId;

    @NotNull(message = "O campo deve ser do tipo UUID")
    @org.hibernate.validator.constraints.UUID(message = "O campo deve ser do tipo UUID")
    private String customerId;

    @NotNull(message = "O campo deve ser do tipo UUID")
    @org.hibernate.validator.constraints.UUID(message = "O campo deve ser do tipo UUID")
    private String creditCardId;

    @NotEmpty(message = "Método de pagamento deve ser preenchido")
    @ValueOfEnum(enumClass = PaymentMethod.class, message = "Método de pagamento inválido")
    private String paymentMethod;

    @NotEmpty(message = "Descrição da compra deve ser preenchida")
    @Size(min = 3, max = 100, message = "Descrição da compra deve ter entre 3 e 100 caracteres")
    private String description;

    @NotNull(message = "Valor deve ser preenchido")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal value;

    @NotEmpty(message = "Status deve ser preenchido")
    @ValueOfEnum(enumClass = Status.class, message = "Status inválido")
    private String status;

    public record PaymentId(
            @NotEmpty(message = "O campo deve ser do tipo UUID")
            @org.hibernate.validator.constraints.UUID(message = "O campo deve ser do tipo UUID")
            String id
    ){
        public PaymentId(String id) {
            this.id = id;
            validate(this);

        }
    }

    public static Payment of(UUID paymentId, UUID customerId, UUID creditCardId, String paymentMethod, String description, BigDecimal value, String status) {
        return new Payment(new PaymentId(paymentId.toString()), customerId.toString(), creditCardId.toString(), paymentMethod, description, value, status);
    }

    public static Payment from(CreditCard creditCard, String description, BigDecimal value, Status status) {

        return new Payment(
                creditCard.getCustomerId(),
                UUID.fromString(creditCard.getCreditCardId().id()),
                PaymentMethod.CARTAO_CREDITO.name(),
                description,
                value,
                status.name()
        );

    }

    public Payment(UUID customerId, UUID creditCardId, String paymentMethod, String description, BigDecimal value, String status) {

        this.customerId = customerId.toString();
        this.creditCardId = creditCardId.toString();
        this.paymentMethod = paymentMethod;
        this.description = description;
        this.value = value;
        this.status = status;
        validate(this);
        this.paymentId = new PaymentId(UUID.randomUUID().toString());

    }

}
