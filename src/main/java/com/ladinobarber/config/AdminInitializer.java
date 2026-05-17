package com.ladinobarber.config;

import com.ladinobarber.modelo.documentos.Administrador;
import com.ladinobarber.modelo.enums.Rol;
import com.ladinobarber.repositorios.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.id}")
    private String adminId;

    @Value("${admin.nombre}")
    private String adminNombre;

    @Value("${admin.correo}")
    private String adminCorreo;

    @Value("${admin.telefono}")
    private String adminTelefono;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {

        if (administradorRepository.findByCorreo(adminCorreo).isEmpty()) {

            Administrador admin = new Administrador();

            admin.setId(adminId);
            admin.setNombre(adminNombre);
            admin.setCorreo(adminCorreo);
            admin.setTelefono(adminTelefono);
            admin.setRol(Rol.ADMINISTRADOR);
            admin.setPassword(passwordEncoder.encode(adminPassword));

            administradorRepository.save(admin);

            System.out.println("✅ ADMINISTRADOR CREADO");
            System.out.println("📧 Email: " + adminCorreo);
            System.out.println("🔑 Password: " + adminPassword);

        } else {

            System.out.println("ℹ️ El administrador ya existe");

        }

    }
}