package com.github.rafaelfernandes.creditcard.adapter.in.web;

import com.github.rafaelfernandes.creditcard.common.exception.CustomerNotFoundException;
import com.github.rafaelfernandes.creditcard.common.exception.NumberCreditCardByCpfException;
import com.github.rafaelfernandes.creditcard.common.exception.NumberCreditCardForCpfExistsException;
import com.github.rafaelfernandes.creditcard.common.exception.ResponseError;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseError> errorValidation(ValidationException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler({NumberCreditCardByCpfException.class})
    public ResponseEntity<ResponseError> errorValidation(NumberCreditCardByCpfException exception){
        return ResponseEntity
                .status(HttpStatus.valueOf(exception.getStatus()))
                .body(new ResponseError(exception.getMessage(), exception.getStatus()));
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<ResponseError> errorValidation(CustomerNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.valueOf(exception.getStatus()))
                .body(new ResponseError(exception.getMessage(), exception.getStatus()));
    }

    @ExceptionHandler({NumberCreditCardForCpfExistsException.class})
    public ResponseEntity<ResponseError> errorValidation(NumberCreditCardForCpfExistsException exception){
        return ResponseEntity
                .status(HttpStatus.valueOf(exception.getStatus()))
                .body(new ResponseError(exception.getMessage(), exception.getStatus()));
    }




}
