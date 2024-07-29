package com.github.rafaelfernandes.customer.adapter.in.web.response;

import java.util.UUID;

public record CustomerIdResponse(
        UUID id_cliente
) {

        public static CustomerIdResponse fromEntity(UUID id){
            return new CustomerIdResponse(id);
        }
}
