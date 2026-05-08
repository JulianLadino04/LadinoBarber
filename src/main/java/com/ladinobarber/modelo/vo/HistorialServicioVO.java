package com.ladinobarber.modelo.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistorialServicioVO {

    private String citaId;

    private String servicioId;

    private String nombreServicio;

    private String barberoId;

    private String nombreBarbero;

    private LocalDate fechaRealizacion;

    private BigDecimal precioPagado;

    private boolean bonoAplicado;
}