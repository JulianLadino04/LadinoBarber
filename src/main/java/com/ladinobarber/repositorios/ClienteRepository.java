package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

    /**
     * Busca un cliente por su correo electrónico
     */
    Optional<Cliente> findByCorreo(String correo);

    /**
     * Busca un cliente por su teléfono
     */
    Optional<Cliente> findByTelefono(String telefono);

    /**
     * Verifica si existe un cliente con el correo dado
     */
    boolean existsByCorreo(String correo);

    /**
     * Verifica si existe un cliente con el teléfono dado
     */
    boolean existsByTelefono(String telefono);

    /**
     * Busca un cliente por su código de recuperación
     */
    Optional<Cliente> findByCodigoRecuperacion(String codigoRecuperacion);
}