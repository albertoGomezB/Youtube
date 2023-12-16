package com.agb.securityjwt2023.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

    @NotBlank(message = "El campo email no debe estar vacío")
    @Email(message = "El campo email debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "El campo password no debe estar vacío")
    private String password;
}
