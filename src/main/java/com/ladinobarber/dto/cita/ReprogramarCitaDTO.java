package com.ladinobarber.dto.cita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO para reprogramar una cita a otro horario.
 */
public record ReprogramarCitaDTO(
    @NotBlank String citaId,
    @NotNull LocalDate nuevaFecha,
    @NotNull LocalTime nuevaHoraInicio,
    @Length(max=300) String observaciones
) {}
