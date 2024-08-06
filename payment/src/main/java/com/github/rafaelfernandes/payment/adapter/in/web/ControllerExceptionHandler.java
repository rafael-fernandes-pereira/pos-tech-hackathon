package com.github.rafaelfernandes.payment.adapter.in.web;

import com.github.rafaelfernandes.payment.common.exception.*;
import com.github.rafaelfernandes.payment.common.response.ResponseError;
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

    @ExceptionHandler({CreditCardLimiteExceededFoundException.class})
    public ResponseEntity<ResponseError> errorValidation(CreditCardLimiteExceededFoundException exception){
        return ResponseEntity
                .status(HttpStatus.valueOf(exception.getStatus()))
                .body(new ResponseError(exception.getMessage(), exception.getStatus()));
    }

    @ExceptionHandler({CreditCardNotFoundException.class})
    public ResponseEntity<ResponseError> errorValidation(CreditCardNotFoundException exception){
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

    @ExceptionHandler({NumberCreditCardByCpfException.class})
    public ResponseEntity<ResponseError> errorValidation(NumberCreditCardByCpfException exception){
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
