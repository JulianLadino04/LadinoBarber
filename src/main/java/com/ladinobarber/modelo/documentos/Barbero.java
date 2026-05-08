package com.ladinobarber.modelo.documentos;

import com.ladinobarber.modelo.vo.HorarioDisponible;
import com.ladinobarber.modelo.vo.Usuario;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "barberos")

public class Barbero extends Usuario {

    private String descripcion;

    @Field("foto_url")
    private String fotoUrl;

    private boolean activo;

    private List<HorarioDisponible> horarios;

    @Field("codigo_recuperacion")
    private String codigoRecuperacion;

    @Field("fecha_expiracion_codigo_recuperacion")
    private LocalDateTime fechaExpiracionCodigoRecuperacion;

    @Field("fecha_registro")
    private LocalDateTime fechaRegistro;

    @Field("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}