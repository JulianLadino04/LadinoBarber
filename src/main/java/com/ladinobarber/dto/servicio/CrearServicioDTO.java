package com.ladinobarber.dto.servicio;

import com.ladinobarber.modelo.enums.TipoServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;

/**
 * DTO para crear un nuevo servicio.
 */
public record CrearServicioDTO(
    @NotBlank @Length(max=100) String nombre,
    @NotBlank @Length(max=300) String descripcion,
    @NotNull TipoServicio tipo,
    @NotNull @Positive BigDecimal precio,
    @NotNull int duracionMinutos,
    String imagenUrl
) {}
