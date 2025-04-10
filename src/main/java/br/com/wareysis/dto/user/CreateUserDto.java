package br.com.wareysis.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(

        @NotBlank(message = "E-mail não pode ser vazio")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        String password,

        @NotBlank(message = "Nome completo não pode ser vazio")
        String fullName
) {

}
