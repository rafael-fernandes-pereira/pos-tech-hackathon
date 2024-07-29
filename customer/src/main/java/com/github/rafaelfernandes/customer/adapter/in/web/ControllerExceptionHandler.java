package com.github.rafaelfernandes.customer.adapter.in.web;

import com.github.rafaelfernandes.customer.common.exception.CustomerExistsCpfException;
import com.github.rafaelfernandes.customer.common.exception.CustomerExistsEmailException;
import com.github.rafaelfernandes.customer.common.exception.CustomerNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.rafaelfernandes.customer.common.response.ResponseError;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseError> errorValidation(ValidationException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({CustomerExistsCpfException.class})
    public ResponseEntity<ResponseError> errorValidation(CustomerExistsCpfException exception){
        return ResponseEntity
                .status(HttpStatus.valueOf(exception.getStatus()))
                .body(new ResponseError(exception.getMessage(), exception.getStatus()));
    }

    @ExceptionHandler({CustomerExistsEmailException.class})
    public ResponseEntity<ResponseError> errorValidation(CustomerExistsEmailException exception){
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




}
