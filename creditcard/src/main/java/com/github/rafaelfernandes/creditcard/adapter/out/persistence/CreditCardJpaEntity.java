package com.github.rafaelfernandes.creditcard.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "tbl_credit_card",
        indexes = {
                @Index(name = "idx_credit_card_document", columnList = "document")
        }
)
public class CreditCardJpaEntity {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String document;

    private String number;

    private String cvv;

    private String expirationDate;

    private BigDecimal limitValue;

}
