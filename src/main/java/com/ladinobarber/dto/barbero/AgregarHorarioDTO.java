package com.ladinobarber.dto.barbero;

import com.ladinobarber.modelo.enums.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * DTO para agregar o actualizar un horario de barbero.
 */
public record AgregarHorarioDTO(
    @NotBlank String barberoId,
    @NotNull DiaSemana dia,
    @NotNull LocalTime horaInicio,
    @NotNull LocalTime horaFin
) {}
