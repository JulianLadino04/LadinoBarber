package com.ladinobarber.dto.cita;

import com.ladinobarber.modelo.enums.EstadoCita;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * DTO para detalle completo de una cita.
 */
public record InformacionCitaDTO(
    String id,
    String clienteId,
    String nombreCliente,
    String telefonoCliente,
    String barberoId,
    String nombreBarbero,
    String servicioId,
    String nombreServicio,
    LocalDate fecha,
    LocalTime horaInicio,
    LocalTime horaFin,
    BigDecimal precioBase,
    BigDecimal precioFinal,
    boolean bonoAplicado,
    EstadoCita estado,
    String observaciones,
    String citaOriginalId,
    boolean confirmacionEnviada,
    boolean recordatorioEnviado,
    LocalDateTime fechaCreacion
) {}
