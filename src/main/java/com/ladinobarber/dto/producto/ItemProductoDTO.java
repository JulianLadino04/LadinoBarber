package com.ladinobarber.dto.producto;

import java.math.BigDecimal;

/**
 * DTO para item de lista de productos en el catálogo del cliente.
 */
public record ItemProductoDTO(
    String id,
    String nombre,
    String marca,
    BigDecimal precio,
    int stock,
    String imagenUrl
) {}
