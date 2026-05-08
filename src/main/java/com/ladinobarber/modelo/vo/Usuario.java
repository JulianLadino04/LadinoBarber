package com.ladinobarber.modelo.vo;

import com.ladinobarber.modelo.enums.Rol;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "usuarios")
public abstract class Usuario {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String contrasena;
    private String correo;
    private String telefono;
    private Rol rol;
}