package com.ladinobarber.modelo.documentos;

import com.ladinobarber.modelo.vo.HistorialServicioVO;
import com.ladinobarber.modelo.vo.PromocionVO;
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
@Document(collection = "clientes")

public class Cliente extends Usuario {

    @Field("favoritos_ids")
    private List<String> favoritosIds;

    @Field("historial_servicios")
    private List<HistorialServicioVO> historialServicios;

    private PromocionVO promocion;

    @Field("codigo_recuperacion")
    private String codigoRecuperacion;

    @Field("fecha_expiracion_codigo_recuperacion")
    private LocalDateTime fechaExpiracionCodigoRecuperacion;

    @Field("fecha_registro")
    private LocalDateTime fechaRegistro;

    @Field("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
