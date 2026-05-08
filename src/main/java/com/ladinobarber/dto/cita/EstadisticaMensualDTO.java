package com.ladinobarber.dto.cita;

import java.math.BigDecimal;

/**
 * DTO para estadísticas calculadas para el panel del administrador.
 */
public record EstadisticaMensualDTO(
    int anio,
    int mes,
    int totalCortesRealizados,
    BigDecimal ingresosTotales,
    int citasCanceladas,
    int citasReprogramadas,
    int bonosAplicados
) {}
