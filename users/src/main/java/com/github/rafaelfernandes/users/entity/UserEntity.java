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

    private String name;

    private String email;

    private String password;

    private String document;

    private UserRoles userRoles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private ContactEntity contact;

}
