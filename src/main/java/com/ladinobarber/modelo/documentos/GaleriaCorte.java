package com.ladinobarber.modelo.documentos;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
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
@Document(collection = "galeria_cortes")
public class GaleriaCorte {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("imagen_url")
    private String imagenUrl;

    private String descripcion;

    private List<String> etiquetas;

    @Indexed
    @Field("barbero_id")
    private String barberoId;

    @Field("nombre_barbero")
    private String nombreBarbero;

    @Field("cita_id")
    private String citaId;

    @Field("total_favoritos")
    private int totalFavoritos;

    private boolean visible;

    @CreatedDate
    @Field("fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @LastModifiedDate
    @Field("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}