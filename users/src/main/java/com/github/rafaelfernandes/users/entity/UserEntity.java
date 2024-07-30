package com.github.rafaelfernandes.users.entity;

import java.util.UUID;

import com.github.rafaelfernandes.users.enums.UserRoles;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    private UUID id;

    private String username;

    private String password;

    private UserRoles userRoles;

}
