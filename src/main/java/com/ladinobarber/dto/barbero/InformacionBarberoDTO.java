package com.ladinobarber.dto.barbero;

import com.ladinobarber.dto.barbero.HorarioDisponibleDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para detalle completo del barbero.
 */
public record InformacionBarberoDTO(

        String id,

        String nombre,

        String telefono,

        String correo,

        String descripcion,

        String fotoUrl,

        boolean activo,

        List<HorarioDisponibleDTO> horarios,

        LocalDateTime fechaRegistro

) {}
