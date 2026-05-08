package com.ladinobarber.modelo.documentos;

import com.ladinobarber.modelo.enums.EstadoCita;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "citas")
@CompoundIndexes({
        @CompoundIndex(
                name = "idx_barbero_fecha_hora",
                def = "{'barbero_id': 1, 'fecha': 1, 'hora_inicio': 1}",
                unique = true
        )
})
public class Cita {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    // ─── Relaciones ──────────────────────────────────────────────────────────

    @Indexed
    @Field("cliente_id")
    private String clienteId;

    @Field("nombre_cliente")
    private String nombreCliente;

    @Field("telefono_cliente")
    private String telefonoCliente;

    @Indexed
    @Field("barbero_id")
    private String barberoId;

    @Field("nombre_barbero")
    private String nombreBarbero;

    @Field("servicio_id")
    private String servicioId;

    @Field("nombre_servicio")
    private String nombreServicio;

    // ─── Fecha y hora ────────────────────────────────────────────────────────

    @Indexed
    private LocalDate fecha;

    @Field("hora_inicio")
    private LocalTime horaInicio;

    @Field("hora_fin")
    private LocalTime horaFin;

    // ─── Precio ──────────────────────────────────────────────────────────────

    @Field("precio_base")
    private BigDecimal precioBase;

    @Field("precio_final")
    private BigDecimal precioFinal;

    @Field("bono_aplicado")
    private boolean bonoAplicado;

    // ─── Estado ──────────────────────────────────────────────────────────────

    private EstadoCita estado;

    private String observaciones;

    @Field("cita_original_id")
    private String citaOriginalId;

    // ─── Notificaciones ──────────────────────────────────────────────────────

    @Field("confirmacion_enviada")
    private boolean confirmacionEnviada;

    @Field("recordatorio_enviado")
    private boolean recordatorioEnviado;

    @CreatedDate
    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Field("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}