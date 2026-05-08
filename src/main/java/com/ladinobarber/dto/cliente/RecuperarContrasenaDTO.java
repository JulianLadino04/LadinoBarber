package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * DTO para solicitar recuperación de contraseña por correo.
 */
public record RecuperarContrasenaDTO(

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        @Size(max = 50, message = "El correo no puede superar 50 caracteres")
        String correo

) {}
