package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Barbero;
import java.util.List;

@Repository
public interface BarberoRepository extends MongoRepository<Barbero, String> {

    /**
     * Busca todos los barberos activos.
     * @return lista de barberos activos
     */
    List<Barbero> findByActivoTrue();

    /**
     * Busca un barbero por su correo electrónico.
     * @param correo el correo del barbero
     * @return el barbero encontrado o null si no existe
     */
    Barbero findByCorreo(String correo);

    /**
     * Verifica si existe un barbero con el correo dado.
     * @param correo el correo a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByCorreo(String correo);

    /**
     * Busca un barbero por su código de recuperación.
     * @param codigoRecuperacion el código de recuperación
     * @return el barbero encontrado o null si no existe
     */
    Barbero findByCodigoRecuperacion(String codigoRecuperacion);
}
