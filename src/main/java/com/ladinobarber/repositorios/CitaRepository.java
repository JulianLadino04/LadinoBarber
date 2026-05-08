package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Cita;
import com.ladinobarber.modelo.enums.EstadoCita;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    /**
     * Busca todas las citas de un cliente por su ID.
     * @param clienteId el ID del cliente
     * @return lista de citas del cliente
     */
    List<Cita> findByClienteId(String clienteId);

    /**
     * Busca todas las citas de un barbero por su ID.
     * @param barberoId el ID del barbero
     * @return lista de citas del barbero
     */
    List<Cita> findByBarberoId(String barberoId);

    /**
     * Busca citas por barbero ID, fecha y estado.
     * @param barberoId el ID del barbero
     * @param fecha la fecha de la cita
     * @param estado el estado de la cita
     * @return lista de citas que coinciden
     */
    List<Cita> findByBarberoIdAndFechaAndEstado(String barberoId, LocalDate fecha, EstadoCita estado);

    /**
     * Busca citas por estado.
     * @param estado el estado de la cita
     * @return lista de citas con el estado dado
     */
    List<Cita> findByEstado(EstadoCita estado);

    /**
     * Verifica si existe una cita para un barbero en una fecha y hora de inicio específica.
     * @param barberoId el ID del barbero
     * @param fecha la fecha de la cita
     * @param horaInicio la hora de inicio
     * @return true si existe, false en caso contrario
     */
    boolean existsByBarberoIdAndFechaAndHoraInicio(String barberoId, LocalDate fecha, LocalTime horaInicio);

    /**
     * Busca citas por cliente ID y estado COMPLETADA.
     * @param clienteId el ID del cliente
     * @param estado el estado COMPLETADA
     * @return lista de citas completadas del cliente
     */
    List<Cita> findByClienteIdAndEstado(String clienteId, EstadoCita estado);

    /**
     * Busca citas entre dos fechas de creación para estadísticas mensuales.
     * @param start fecha de inicio
     * @param end fecha de fin
     * @return lista de citas en el rango
     */
    List<Cita> findByFechaCreacionBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Busca citas pendientes con recordatorio no enviado antes de una fecha y hora específica.
     * @param estado el estado PENDIENTE
     * @param fecha la fecha límite
     * @param horaInicio la hora límite
     * @return lista de citas que necesitan recordatorio
     */
    @Query("{ 'estado' : ?0, 'recordatorioEnviado' : false, 'fecha' : { $lt : ?1 }, 'horaInicio' : { $lt : ?2 } }")
    List<Cita> findCitasPendientesSinRecordatorioAntesDe(EstadoCita estado, LocalDate fecha, LocalTime horaInicio);
}
