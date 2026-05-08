package com.ladinobarber.dto.barbero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para actualizar perfil del barbero.
 */
public record EditarBarberoDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        @Size(max = 200, message = "La descripción no puede superar 200 caracteres")
        String descripcion,

        String fotoUrl

) {}
