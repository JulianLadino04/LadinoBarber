package com.ladinobarber.dto.cita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO para que un cliente agende una cita.
 */
public record CrearCitaDTO(
    @NotBlank String clienteId,
    @NotBlank String barberoId,
    @NotBlank String servicioId,
    @NotNull LocalDate fecha,
    @NotNull LocalTime horaInicio
) {}
