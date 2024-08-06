package com.github.rafaelfernandes.payment.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreditCardExpiredDateValidator implements ConstraintValidator<CreditCardExpirationDate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) {
            return false;
        }

        if (value.matches("^(0[1-9]|1[0-2])/\\d{2}$")){

            var month = Integer.parseInt(value.substring(0, 2));
            var year = Integer.parseInt(value.substring(3, 5));

            var date = java.time.LocalDate.now();

            if (year < date.getYear() % 100) {
                return false;
            }

            if (year == date.getYear() % 100 && month < date.getMonthValue()) {
                return false;
            }

        } else {
            return false;
        }

        return true;
    }
}
