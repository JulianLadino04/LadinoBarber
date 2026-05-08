
package com.ladinobarber.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para cambio de contraseña autenticado.
 */
public record CambiarContrasenaDTO(

        @NotBlank(message = "El ID es obligatorio")
        String id,

        @NotBlank(message = "La contraseña actual es obligatoria")
        String passwordActual,

        @NotBlank(message = "La nueva contraseña es obligatoria")
        @Size(min = 6, max = 20, message = "La nueva contraseña debe tener entre 6 y 20 caracteres")
        String passwordNueva

) {}
