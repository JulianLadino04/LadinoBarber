package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO para restablecer con código de recuperación.
 */
public record RestablecerContrasenaDTO(
    @NotBlank String codigoRecuperacion,
    @NotBlank @Length(min=8, max=30) String nuevaContrasena,
    @NotBlank @Length(min=8, max=30) String confirmarContrasena
) {}
