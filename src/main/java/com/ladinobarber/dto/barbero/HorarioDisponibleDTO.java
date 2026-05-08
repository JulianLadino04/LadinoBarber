package com.ladinobarber.dto.barbero;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * DTO que representa un horario disponible del barbero.
 */
public record HorarioDisponibleDTO(
        DayOfWeek diaSemana,
        LocalTime horaInicio,
        LocalTime horaFin,
        boolean disponible
) {}