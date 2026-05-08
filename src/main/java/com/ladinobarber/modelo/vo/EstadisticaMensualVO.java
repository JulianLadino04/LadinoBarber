package com.ladinobarber.modelo.vo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstadisticaMensualVO {

    private int anio;

    private int mes;

    private int totalCortesRealizados;

    private BigDecimal ingresosTotales;

    private int citasCanceladas;

    private int citasReprogramadas;

    private int bonosAplicados;
}