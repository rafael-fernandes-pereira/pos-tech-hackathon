package com.github.rafaelfernandes.users.entity;

import com.github.rafaelfernandes.users.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_contact")
public class ContactEntity {

    @Id
    UUID id;

    private String phone;
    private String zipcode;
    private String street;
    private String city;
    private State state;
    private String country;

    @OneToOne(mappedBy = "contact")
    private UserEntity user;

}
