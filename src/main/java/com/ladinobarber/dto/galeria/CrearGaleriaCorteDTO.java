package com.ladinobarber.dto.galeria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * DTO para publicar una foto en el feed.
 */
public record CrearGaleriaCorteDTO(

        @NotBlank(message = "La URL de la imagen es obligatoria")
        String imagenUrl,

        @Size(max = 300, message = "La descripción no puede superar 300 caracteres")
        String descripcion,

        List<String> etiquetas,

        @NotBlank(message = "El ID del barbero es obligatorio")
        String barberoId,

        String citaId

) {}
