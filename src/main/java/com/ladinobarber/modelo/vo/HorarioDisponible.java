package com.ladinobarber.modelo.vo;

import com.ladinobarber.modelo.enums.DiaSemana;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HorarioDisponible {

    private DiaSemana dia;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private boolean habilitado;
}