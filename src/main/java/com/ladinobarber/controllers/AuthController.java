package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.TokenDTO;
import com.ladinobarber.dto.cliente.CrearClienteDTO;
import com.ladinobarber.dto.cliente.LoginDTO;
import com.ladinobarber.dto.cliente.RecuperarContrasenaDTO;
import com.ladinobarber.dto.cliente.RestablecerContrasenaDTO;
import com.ladinobarber.servicios.interfaces.ClienteService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ClienteService clienteService;

    public AuthController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO dto) throws Exception {

        TokenDTO token = clienteService.login(dto);

        return ResponseEntity.ok(
                new ResponseDTO<>("Inicio de sesión exitoso", token)
        );
    }

    @PostMapping("/recuperar")
    public ResponseEntity<ResponseDTO<String>> iniciarRecuperacion(
            @Valid @RequestBody RecuperarContrasenaDTO dto) throws Exception {

        clienteService.iniciarRecuperacionContrasena(dto);

        return ResponseEntity.ok(
                new ResponseDTO<>("Código de recuperación enviado al correo", null)
        );
    }

    @PostMapping("/restablecer")
    public ResponseEntity<ResponseDTO<String>> restablecer(
            @Valid @RequestBody RestablecerContrasenaDTO dto) throws Exception {

        clienteService.restablecerContrasena(dto);

        return ResponseEntity.ok(
                new ResponseDTO<>("Contraseña actualizada correctamente", null)
        );
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponseDTO<String>> registrar(
            @Valid @RequestBody CrearClienteDTO dto) throws Exception {

        String id = clienteService.crear(dto);

        return ResponseEntity.ok(
                new ResponseDTO<>("Cliente registrado correctamente", id)
        );
    }

}