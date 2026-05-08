package com.ladinobarber.dto.servicio;

import com.ladinobarber.modelo.enums.TipoServicio;
import java.math.BigDecimal;

/**
 * DTO para item de lista de servicios en catálogo y selección de cita.
 */
public record ItemServicioDTO(
    String id,
    String nombre,
    TipoServicio tipo,
    BigDecimal precio,
    int duracionMinutos,
    String imagenUrl
) {}
