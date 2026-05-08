package com.ladinobarber.dto.barbero;

import com.ladinobarber.modelo.enums.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para habilitar o deshabilitar un día específico.
 */
public record CambiarEstadoHorarioDTO(

        @NotBlank(message = "El ID del barbero es obligatorio")
        String barberoId,

        @NotNull(message = "El día es obligatorio")
        DiaSemana dia,

        boolean habilitado

) {}
