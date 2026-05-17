package com.ladinobarber.dto.producto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para detalle completo de un producto.
 */
public record InformacionProductoDTO(

        String id,

        String nombre,

        String descripcion,

        String marca,

        BigDecimal precio,

        int stock,

        String imagenUrl,

        boolean activo

) {}
