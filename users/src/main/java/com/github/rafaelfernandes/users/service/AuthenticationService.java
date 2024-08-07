package com.github.rafaelfernandes.users.service;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.users.controller.LoginRequest;
import com.github.rafaelfernandes.users.exception.UnauthorizedException;
import com.github.rafaelfernandes.users.repository.UserRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRespository respository;

    private final JwtService jwtService;


    public String authenticate(LoginRequest request){
        
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.usuario(),
                            request.senha()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(403, "Invalid username or password");
        }

        var user = respository.findFirstByUsername(request.usuario());

        if (!user.isPresent()){

            throw new UsernameNotFoundException("User not found");

        }

        var userData = user.get();

        var userDetails = new org.springframework.security.core.userdetails.User(userData.getUsername(), userData.getPassword(), new ArrayList<>());

        return jwtService.generateToken(userDetails.getUsername(), userData.getUserRoles(), userData.getId());

    }

    public void validate(String token){
        if (jwtService.isInvalid(token)){
            throw new UnauthorizedException(401, "Invalid token");
        }
    }


}
