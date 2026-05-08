package com.ladinobarber.dto.cita;

import com.ladinobarber.modelo.enums.EstadoCita;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO para item de lista de citas en panel admin o historial del cliente.
 */
public record ItemCitaDTO(

        String id,

        String nombreCliente,

        String nombreBarbero,

        String nombreServicio,

        LocalDate fecha,

        LocalTime horaInicio,

        LocalTime horaFin,

        BigDecimal precioFinal,

        EstadoCita estado

) {}
