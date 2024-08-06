package com.github.rafaelfernandes.creditcard.port.out;

import java.util.Optional;
import java.util.UUID;

public interface CustomerPort {

    Optional<UUID> findIdByCpf(String cpf);
}
