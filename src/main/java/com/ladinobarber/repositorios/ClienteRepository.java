package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

    /**
     * Busca un cliente por su correo electrónico.
     * @param correo el correo del cliente
     * @return el cliente encontrado o null si no existe
     */
    Cliente findByCorreo(String correo);

    /**
     * Busca un cliente por su teléfono.
     * @param telefono el teléfono del cliente
     * @return el cliente encontrado o null si no existe
     */
    Cliente findByTelefono(String telefono);

    /**
     * Verifica si existe un cliente con el correo dado.
     * @param correo el correo a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByCorreo(String correo);

    /**
     * Verifica si existe un cliente con el teléfono dado.
     * @param telefono el teléfono a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByTelefono(String telefono);

    /**
     * Busca un cliente por su código de recuperación.
     * @param codigoRecuperacion el código de recuperación
     * @return el cliente encontrado o null si no existe
     */
    Cliente findByCodigoRecuperacion(String codigoRecuperacion);
}
