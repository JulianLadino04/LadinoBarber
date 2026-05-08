package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO para actualizar perfil del cliente.
 */
public record EditarClienteDTO(
    @NotBlank String id,
    @NotBlank @Length(max=100) String nombre,
    @NotBlank @Length(max=15) String telefono
) {}
