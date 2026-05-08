package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    /**
     * Busca todos los productos activos.
     * @return lista de productos activos
     */
    List<Producto> findByActivoTrue();

    /**
     * Busca productos activos con stock mayor a cero.
     * @return lista de productos activos disponibles
     */
    List<Producto> findByActivoTrueAndStockGreaterThan(int stock);
}
