package com.ladinobarber.dto.servicio;

import com.ladinobarber.modelo.enums.TipoServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DTO para crear un nuevo servicio.
 */
public record CrearServicioDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
        String descripcion,

        @NotNull(message = "El tipo es obligatorio")
        TipoServicio tipo,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser un valor positivo")
        BigDecimal precio,

        @Min(value = 5, message = "La duración mínima es 5 minutos")
        @Max(value = 240, message = "La duración máxima es 240 minutos")
        int duracionMinutos,

        String imagenUrl,

        boolean activo

) {}
