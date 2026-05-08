package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Servicio;
import com.ladinobarber.modelo.enums.TipoServicio;
import java.util.List;

@Repository
public interface ServicioRepository extends MongoRepository<Servicio, String> {

    /**
     * Busca todos los servicios activos.
     * @return lista de servicios activos
     */
    List<Servicio> findByActivoTrue();

    /**
     * Busca servicios activos por tipo.
     * @param tipo el tipo de servicio
     * @return lista de servicios activos del tipo dado
     */
    List<Servicio> findByActivoTrueAndTipo(TipoServicio tipo);
}
