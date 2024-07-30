package com.github.rafaelfernandes.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.rafaelfernandes.users.exception.ResponseError;
import com.github.rafaelfernandes.users.exception.UnauthorizedException;
import com.github.rafaelfernandes.users.exception.UserException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseError> userException(UserException exception){
        
        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
            .status(exception.getStatus())
            .body(response)
        ;

    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseError> unauthorizedException(UnauthorizedException exception){
        
        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
            .status(exception.getStatus())
            .body(response)
        ;

    }




}
