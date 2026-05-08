package com.ladinobarber.dto.galeria;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.List;

/**
 * DTO para publicar una foto en el feed.
 */
public record CrearGaleriaCorteDTO(
    @NotBlank String imagenUrl,
    @Length(max=300) String descripcion,
    List<String> etiquetas,
    @NotBlank String barberoId,
    String citaId
) {}
