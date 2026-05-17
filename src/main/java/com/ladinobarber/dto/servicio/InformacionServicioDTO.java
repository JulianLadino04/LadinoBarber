package com.ladinobarber.dto.servicio;

import com.ladinobarber.modelo.enums.TipoServicio;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para detalle completo de un servicio.
 */
public record InformacionServicioDTO(

        String nombre,

        String descripcion,

        TipoServicio tipo,

        BigDecimal precio,

        int duracionMinutos,

        String imagenUrl,

        boolean activo

) {}
