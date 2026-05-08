package com.ladinobarber.dto.cita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO para reprogramar una cita a otro horario.
 */
public record ReprogramarCitaDTO(

        @NotBlank(message = "El ID de la cita es obligatorio")
        String citaId,

        @NotNull(message = "La nueva fecha es obligatoria")
        LocalDate nuevaFecha,

        @NotNull(message = "La nueva hora de inicio es obligatoria")
        LocalTime nuevaHoraInicio,

        @Size(max = 100, message = "Las observaciones no pueden superar 100 caracteres")
        String observaciones

) {}
