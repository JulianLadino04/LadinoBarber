package com.ladinobarber.dto.cliente;

import com.ladinobarber.modelo.enums.EstadoPromocion;
import java.time.LocalDateTime;

/**
 * DTO para detalle completo del perfil del cliente.
 */
public record InformacionClienteDTO(
    String id,
    String nombre,
    String telefono,
    String correo,
    int cortesAcumulados,
    EstadoPromocion estadoPromocion,
    int totalBonosRedimidos,
    LocalDateTime fechaUltimoBono,
    LocalDateTime fechaRegistro
) {}
