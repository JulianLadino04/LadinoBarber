package com.ladinobarber.modelo.vo;

import com.ladinobarber.modelo.enums.EstadoPromocion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromocionVO {

    private int cortesAcumulados;

    private EstadoPromocion estado;

    private LocalDateTime fechaUltimoBono;

    private int totalBonosRedimidos;
}