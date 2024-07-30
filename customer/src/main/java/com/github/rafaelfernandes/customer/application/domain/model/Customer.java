package com.github.rafaelfernandes.customer.application.domain.model;

import com.github.rafaelfernandes.customer.common.enums.State;
import com.github.rafaelfernandes.customer.common.validation.ValidationCep;
import com.github.rafaelfernandes.customer.common.validation.ValidationContactNumber;
import com.github.rafaelfernandes.customer.common.validation.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

import static com.github.rafaelfernandes.customer.common.validation.Validation.validate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    private final CustomerId customerId;

    @NotNull(message = "CPF deve ser preenchido")
    @Size(min = 11, max = 14, message = "CPF deve conter 11 caracteres (sem pontuacao) e 14 (com pontuação)")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Nome deve ser preenchido")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull(message = "Email deve ser preenchido")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "Contato deve ser preenchido")
    private Contact contact;

    public record CustomerId(
            @NotEmpty(message = "O campo deve ser do tipo UUID")
            @org.hibernate.validator.constraints.UUID(message = "O campo deve ser do tipo UUID")
            String id
    ){
        public CustomerId(String id) {
            this.id = id;
            validate(this);

        }
    }

    @Value
    public static class Contact {

        @NotNull(message = "Telefone deve ser preenchido")
        @Size(min = 17, max = 17, message = "Telefone deve conter {min} caracteres")
        @ValidationContactNumber(message = "Telefone inválido. O telefone deve seguir o padrão +XX XX XXXXX-XXXX")
        String telefone;

        @NotEmpty(message = "Rua deve ser preenchida")
        @Size( min = 10, max = 150, message = "Rua deve ter no minimo {min} e máximo {max} caracteres")
        String rua;

        @NotEmpty(message = "Cidade deve ser preenchida")
        @Size( min = 3, max = 60, message = "Cidade deve ter no minimo 3 e no máximo 60 caracteres")
        String cidade;

        @NotEmpty(message = "Estado deve ser preenchido")
        @ValueOfEnum(enumClass = State.class, message = "Estado inválido")
        String estado;

        @NotEmpty(message = "CEP deve ser preenchido")
        @Size(min = 9, max = 9, message = "CEP deve conter 8 caracteres")
        @ValidationCep(message = "CEP inválido. O CEP deve seguir o padrão XXXXX-XXX")
        String cep;

        @NotNull(message = "País deve ser preenchido")
        @Size(min = 3, max = 60, message = "País deve ter no minimo 3 e no máximo 60 caracteres")
        String pais;

        public Contact(String telefone, String rua, String cidade, String estado, String cep, String pais) {
            this.telefone = telefone;
            this.rua = rua;
            this.cidade = cidade;
            this.estado = estado;
            this.cep = cep;
            this.pais = pais;
            validate(this);
        }

    }

    public Customer(String cpf, String nome, String email, Contact contact) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.contact = contact;
        validate(this);

        this.customerId = new CustomerId(UUID.randomUUID().toString());
    }

    public static Customer of(String id, String cpf, String nome, String email, Contact contact) {
        return new Customer(new CustomerId(id), cpf, nome, email, contact);
    }

}
