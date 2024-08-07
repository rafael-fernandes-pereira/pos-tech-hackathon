package com.github.rafaelfernandes.payment.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "tbl_payment",
        indexes = {
                @Index(name = "idx_payment", columnList = "customer_id")
        }
)

public class PaymentJpaEntity {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private UUID customerId;

    private UUID creditCardId;

    private String document;

    private String description;

    private String paymentMethod;

    private String status;

    private BigDecimal value;

}
