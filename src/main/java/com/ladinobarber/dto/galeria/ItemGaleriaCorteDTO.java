package com.ladinobarber.dto.galeria;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para item del feed público del cliente.
 */
public record ItemGaleriaCorteDTO(

        String id,

        String imagenUrl,

        String descripcion,

        String nombreBarbero,

        List<String> etiquetas,

        int totalFavoritos,

        LocalDateTime fechaPublicacion

) {}
