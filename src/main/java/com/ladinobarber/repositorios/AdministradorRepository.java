package com.ladinobarber.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ladinobarber.modelo.documentos.Administrador;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String> {

    /**
     * Busca un administrador por su correo electrónico
     * @param correo correo del administrador
     * @return administrador encontrado (si existe)
     */
    Optional<Administrador> findByCorreo(String correo);

    /**
     * Busca un administrador por su número de teléfono
     * @param telefono teléfono del administrador
     * @return administrador encontrado (si existe)
     */
    Optional<Administrador> findByTelefono(String telefono);

    /**
     * Verifica si ya existe un administrador con ese correo
     * @param correo correo a validar
     * @return true si existe, false si no
     */
    boolean existsByCorreo(String correo);

    /**
     * Verifica si ya existe un administrador con ese teléfono
     * @param telefono teléfono a validar
     * @return true si existe, false si no
     */
    boolean existsByTelefono(String telefono);

    /**
     * Busca un administrador por su código de recuperación de contraseña
     * @param codigoRecuperacion código enviado al administrador
     * @return administrador asociado al código
     */
    Optional<Administrador> findByCodigoRecuperacion(String codigoRecuperacion);
}