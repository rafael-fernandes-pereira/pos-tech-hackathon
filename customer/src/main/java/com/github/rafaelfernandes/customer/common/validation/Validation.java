package com.github.rafaelfernandes.customer.common.validation;

import com.github.rafaelfernandes.customer.adapter.in.web.request.CustomerRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

@Service
public class Validation {

    private Validator validatorA;

    private static final Validator validator =
            buildDefaultValidatorFactory().getValidator();

    public Validation(Validator validatorA){
        this.validatorA = validatorA;
    }

    public List<String> cliente(CustomerRequest request){

        List<String> errors = new ArrayList<>();

        Set<ConstraintViolation<CustomerRequest>> violacoesCliente = validatorA.validate(request);

        if (!violacoesCliente.isEmpty()){
            List<String> errosCliente = violacoesCliente.stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            errors.addAll(errosCliente);
        }

        return errors;

    }

    public static <T> void validate(T subject) {
        Set<ConstraintViolation<T>> violations = validator.validate(subject);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
