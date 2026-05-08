package com.ladinobarber.dto.cliente;

/**
 * DTO para item de lista de clientes en panel admin.
 */
public record ItemClienteDTO(

        String id,

        String nombre,

        String correo,

        String telefono,

        boolean activo

) {}
