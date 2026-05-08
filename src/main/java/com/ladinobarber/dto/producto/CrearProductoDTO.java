package com.ladinobarber.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;

/**
 * DTO para crear un producto.
 */
public record CrearProductoDTO(
    @NotBlank @Length(max=100) String nombre,
    @NotBlank @Length(max=300) String descripcion,
    @NotBlank @Length(max=100) String marca,
    @NotNull @Positive BigDecimal precio,
    @NotNull int stock,
    String imagenUrl
) {}
