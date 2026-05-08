package com.ladinobarber.dto.galeria;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para detalle completo de una publicación.
 */
public record InformacionGaleriaCorteDTO(

        String id,

        String imagenUrl,

        String descripcion,

        List<String> etiquetas,

        String barberoId,

        String nombreBarbero,

        int totalFavoritos,

        boolean visible,

        LocalDateTime fechaPublicacion

) {}
