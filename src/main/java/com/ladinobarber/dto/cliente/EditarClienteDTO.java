package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para actualizar perfil del cliente.
 */
public record EditarClienteDTO(

        @NotBlank(message = "El ID es obligatorio")
        String id,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
        String telefono

) {}
