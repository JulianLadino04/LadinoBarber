package com.ladinobarber.dto.cita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para confirmar, completar o cancelar una cita.
 */
public record GestionarCitaDTO(

        @NotBlank(message = "El ID de la cita es obligatorio")
        String citaId,

        @Size(max = 100, message = "Las observaciones no pueden superar 100 caracteres")
        String observaciones

) {}
