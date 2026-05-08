package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

/**
 * DTO para autenticación de cualquier usuario del sistema.
 */
public record LoginDTO(

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        @Size(max = 100, message = "El correo no puede superar 100 caracteres")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 30, message = "La contraseña debe tener entre 8 y 30 caracteres")
        String contrasena

) {}
