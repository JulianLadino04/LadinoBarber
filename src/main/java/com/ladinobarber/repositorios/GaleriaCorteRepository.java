package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.GaleriaCorte;
import java.util.List;

@Repository
public interface GaleriaCorteRepository extends MongoRepository<GaleriaCorte, String> {

    /**
     * Busca todas las fotos visibles ordenadas por fecha de publicación descendente.
     * @return lista de fotos visibles ordenadas por fecha
     */
    List<GaleriaCorte> findByVisibleTrueOrderByFechaPublicacionDesc();

    /**
     * Busca fotos visibles por barbero ID.
     * @param barberoId el ID del barbero
     * @return lista de fotos visibles del barbero
     */
    List<GaleriaCorte> findByVisibleTrueAndBarberoId(String barberoId);

    /**
     * Busca fotos cuyos IDs estén en una lista de favoritos IDs.
     * @param favoritosIds lista de IDs de fotos favoritas
     * @return lista de fotos favoritas
     */
    List<GaleriaCorte> findByIdIn(List<String> favoritosIds);
}
