package com.github.rafaelfernandes.users.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.model.CustomUserDetails;
import com.github.rafaelfernandes.users.repository.UserRespository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRespository respository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = respository.findFirstByUsername(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
