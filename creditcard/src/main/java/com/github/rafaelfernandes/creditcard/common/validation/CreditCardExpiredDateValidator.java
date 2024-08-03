package com.github.rafaelfernandes.creditcard.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreditCardExpiredDateValidator implements ConstraintValidator<CreditCardExpirationDate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value.matches("^(0[1-9]|1[0-2])/\\d{2}$");
    }
}
