package com.github.rafaelfernandes.customer.adapter.out.persistence;

import com.github.rafaelfernandes.customer.common.enums.State;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_contact")
public class ContactJpaEntity {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String phone;
    private String zipcode;
    private String street;
    private String city;
    private String state;
    private String country;

    @OneToOne(mappedBy = "contact")
    private CustomerJpaEntity user;

}
