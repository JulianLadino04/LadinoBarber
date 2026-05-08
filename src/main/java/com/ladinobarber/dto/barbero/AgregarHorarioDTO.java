package com.ladinobarber.dto.barbero;

import com.ladinobarber.modelo.enums.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * DTO para agregar o actualizar un horario de barbero.
 */
public record AgregarHorarioDTO(

        @NotBlank(message = "El ID del barbero es obligatorio")
        String barberoId,

        @NotNull(message = "El día es obligatorio")
        DiaSemana dia,

        @NotNull(message = "La hora de inicio es obligatoria")
        LocalTime horaInicio,

        @NotNull(message = "La hora de fin es obligatoria")
        LocalTime horaFin

) {}
