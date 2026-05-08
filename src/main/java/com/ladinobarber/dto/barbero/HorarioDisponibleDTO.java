package com.ladinobarber.dto.barbero;

import com.ladinobarber.modelo.enums.DiaSemana;
import java.time.LocalTime;

/**
 * DTO que representa un horario disponible del barbero.
 */
public record HorarioDisponibleDTO(

        DiaSemana dia,

        LocalTime horaInicio,

        LocalTime horaFin,

        boolean habilitado

) {}