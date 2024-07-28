package com.github.rafaelfernandes.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.rafaelfernandes.users.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserIdResponse> register(@RequestBody UserRegisterRequest request){
        
        var newUser = userService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponse> findById(@PathVariable String id){

        var user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

}
