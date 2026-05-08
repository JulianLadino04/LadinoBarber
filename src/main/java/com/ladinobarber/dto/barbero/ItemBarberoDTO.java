package com.ladinobarber.dto.barbero;

/**
 * DTO para item de lista de barberos en selección de cita.
 */
public record ItemBarberoDTO(
    String id,
    String nombre,
    String descripcion,
    String fotoUrl,
    boolean activo
) {}
