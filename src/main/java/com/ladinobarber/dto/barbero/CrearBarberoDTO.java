package com.ladinobarber.dto.barbero;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO para registrar un nuevo barbero.
 */
public record CrearBarberoDTO(
    @NotBlank @Length(max=100) String nombre,
    @NotBlank @Length(max=15) String telefono,
    @NotBlank @Length(max=100) @Email String correo,
    @NotBlank @Length(min=8, max=30) String contrasena,
    @Length(max=300) String descripcion,
    String fotoUrl
) {}
