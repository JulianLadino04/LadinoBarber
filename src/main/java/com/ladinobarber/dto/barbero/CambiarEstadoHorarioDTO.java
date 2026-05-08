package com.ladinobarber.dto.barbero;

import com.ladinobarber.modelo.enums.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para habilitar o deshabilitar un día específico.
 */
public record CambiarEstadoHorarioDTO(
    @NotBlank String barberoId,
    @NotNull DiaSemana dia,
    boolean habilitado
) {}
