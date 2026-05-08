package com.ladinobarber.dto.barbero;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para registrar un nuevo barbero.
 */
public record CrearBarberoDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
        String telefono,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        @Size(max = 100, message = "El correo no puede superar 100 caracteres")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 30, message = "La contraseña debe tener entre 8 y 30 caracteres")
        String contrasena,

        @Size(max = 300, message = "La descripción no puede superar 300 caracteres")
        String descripcion,

        String fotoUrl

) {}
