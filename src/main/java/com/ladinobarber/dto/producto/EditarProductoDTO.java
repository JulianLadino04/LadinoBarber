package com.ladinobarber.dto.producto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * DTO para editar un producto existente.
 */
public record EditarProductoDTO(

        @NotBlank(message = "El ID es obligatorio")
        String id,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
        String descripcion,

        @NotBlank(message = "La marca es obligatoria")
        @Size(min = 2, max = 20, message = "La marca debe tener entre 2 y 20 caracteres")
        String marca,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser un valor positivo")
        BigDecimal precio,

        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        String imagenUrl

) {}
