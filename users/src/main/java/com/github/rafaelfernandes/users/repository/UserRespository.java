package com.github.rafaelfernandes.users.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.rafaelfernandes.users.entity.UserEntity;

@Repository
public interface UserRespository extends JpaRepository<UserEntity, UUID>{

    Optional<UserEntity> findFirstByEmail(String email);

}
