package com.github.rafaelfernandes.creditcard.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditCardExpiredDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardExpirationDate {

    String message() default "Data de validade do cartão inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
