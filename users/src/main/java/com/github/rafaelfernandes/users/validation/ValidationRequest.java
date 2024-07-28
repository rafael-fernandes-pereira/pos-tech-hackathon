package com.github.rafaelfernandes.users.validation;

import com.github.rafaelfernandes.users.controller.UserRegisterRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ValidationRequest {

    private Validator validator;

    public ValidationRequest(Validator validator){
        this.validator = validator;
    }

    public List<String> cliente(UserRegisterRequest request){

        List<String> errors = new ArrayList<>();

        Set<ConstraintViolation<UserRegisterRequest>> violacoesCliente = validator.validate(request);

        if (!violacoesCliente.isEmpty()){
            List<String> errosCliente = violacoesCliente.stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            errors.addAll(errosCliente);
        }

        return errors;

    }

}
