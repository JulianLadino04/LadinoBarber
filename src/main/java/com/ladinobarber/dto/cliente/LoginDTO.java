package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO para autenticación de cualquier usuario del sistema.
 */
public record LoginDTO(
    @NotBlank @Email @Length(max=100) String correo,
    @NotBlank @Length(min=8, max=30) String contrasena
) {}
