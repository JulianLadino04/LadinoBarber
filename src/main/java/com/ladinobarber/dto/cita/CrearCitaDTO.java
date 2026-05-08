package com.ladinobarber.dto.cita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO para que un cliente agende una cita.
 */
public record CrearCitaDTO(

        @NotBlank(message = "El ID del cliente es obligatorio")
        String clienteId,

        @NotBlank(message = "El ID del barbero es obligatorio")
        String barberoId,

        @NotBlank(message = "El ID del servicio es obligatorio")
        String servicioId,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @NotNull(message = "La hora de inicio es obligatoria")
        LocalTime horaInicio

) {}
