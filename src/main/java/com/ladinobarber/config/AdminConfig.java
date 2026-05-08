package com.ladinobarber.config;

import com.ladinobarber.modelo.enums.Rol;
import lombok.Getter;

@Getter
public class AdminConfig {

    public static final String ID       = "admin-ladino-barber";
    public static final String NOMBRE   = "Administrador";
    public static final String CORREO   = "admin@ladinobarber.com";
    public static final String TELEFONO = "+573000000000";
    public static final Rol    ROL      = Rol.ADMINISTRADOR;

    // La contraseña se carga desde application.properties o variable de entorno
    // Ejemplo en application.properties:
    //   admin.password=TuPasswordSeguro123
}