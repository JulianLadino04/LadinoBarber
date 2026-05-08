package com.ladinobarber.dto.cita;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO para confirmar, completar o cancelar una cita.
 */
public record GestionarCitaDTO(
    @NotBlank String citaId,
    @Length(max=300) String observaciones
) {}
