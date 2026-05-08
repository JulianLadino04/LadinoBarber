package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

/**
 * DTO para restablecer con código de recuperación.
 */
public record RestablecerContrasenaDTO(

        @NotBlank(message = "El código de recuperación es obligatorio")
        String codigoRecuperacion,

        @NotBlank(message = "La nueva contraseña es obligatoria")
        @Size(min = 8, max = 30, message = "La nueva contraseña debe tener entre 8 y 30 caracteres")
        String nuevaContrasena,

        @NotBlank(message = "La confirmación de contraseña es obligatoria")
        @Size(min = 8, max = 30, message = "La confirmación de contraseña debe tener entre 8 y 30 caracteres")
        String confirmarContrasena

) {}
