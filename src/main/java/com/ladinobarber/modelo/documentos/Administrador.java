package com.ladinobarber.modelo.documentos;

import com.ladinobarber.modelo.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "administradores")
public class Administrador {

    /**
     * Identificador único del administrador
     */
    @Id
    private String id;

    /**
     * Nombre completo del administrador
     */
    private String nombre;

    /**
     * Correo electrónico (usado para login)
     */
    private String correo;

    /**
     * Número de teléfono del administrador
     */
    private String telefono;

    /**
     * Contraseña encriptada
     */
    private String password;

    /**
     * Rol del usuario (ADMINISTRADOR)
     */
    private Rol rol;

    /**
     * Código usado para recuperación de contraseña
     */
    private String codigoRecuperacion;

}