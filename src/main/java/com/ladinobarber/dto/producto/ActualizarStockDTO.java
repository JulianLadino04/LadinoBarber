package com.ladinobarber.dto.producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para actualizar únicamente el stock de un producto.
 */
public record ActualizarStockDTO(
    @NotBlank String id,
    @Min(0) int nuevoStock
) {}
