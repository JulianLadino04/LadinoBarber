package com.ladinobarber.dto.barbero;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO para actualizar perfil del barbero.
 */
public record EditarBarberoDTO(
    @NotBlank String id,
    @NotBlank @Length(max=100) String nombre,
    @Length(max=300) String descripcion,
    String fotoUrl
) {}
