package com.github.rafaelfernandes.creditcard.application.model;

import com.github.rafaelfernandes.creditcard.common.validation.CreditCardExpirationDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static com.github.rafaelfernandes.creditcard.common.validation.Validation.validate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCard {

    private final CreditCardId creditCardId;

    @NotNull(message = "CPF deve ser preenchido")
    @Size(min = 11, max = 14, message = "CPF deve conter 11 caracteres (sem pontuacao) e 14 (com pontuação)")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Número do cartão deve ser preenchido")
    @Size(min = 19, max = 19, message = "Número do cartão deve conter 19 caracteres")
    @CreditCardNumber(message = "Número do cartão inválido (validação com algoritimo Luhn)", ignoreNonDigitCharacters = true)
    private String numero;

    @NotNull(message = "Data de validade do cartão deve ser preenchida")
    @Size(min = 5, max = 5, message = "Data de validade do cartão deve conter 5 caracteres")
    @CreditCardExpirationDate()
    private String dataValidade;

    @NotNull(message = "Código de segurança do cartão deve ser preenchido")
    @Size(min = 3, max = 3, message = "Código de segurança do cartão deve conter 3 caracteres")
    @Positive(message = "Código de segurança do cartão deve ser positivo")
    private String codigoSeguranca;

    @NotNull(message = "Limite do cartão deve ser preenchido")
    @Positive(message = "Limite do cartão deve ser positivo")
    private BigDecimal limite;

    public static CreditCard of(String creditCardId, String cpf, String number, String expirationDate, String cvv, BigDecimal limit) {
        return new CreditCard(new CreditCardId(creditCardId), cpf, number, expirationDate, cvv, limit);
    }


    public record CreditCardId(
            @NotEmpty(message = "O campo deve ser do tipo UUID")
            @org.hibernate.validator.constraints.UUID(message = "O campo deve ser do tipo UUID")
            String id
    ){
        public CreditCardId(String id) {
            this.id = id;
            validate(this);

        }
    }

    public CreditCard(String cpf, String numero, String dataValidade, String codigoSeguranca, BigDecimal limite) {
        this.cpf = cpf;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
        this.limite = limite;
        validate(this);

        this.creditCardId = new CreditCardId(UUID.randomUUID().toString());

    }

}
