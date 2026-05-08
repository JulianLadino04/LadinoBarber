package com.ladinobarber.servicios.interfaces;

import com.ladinobarber.dto.TokenDTO;
import com.ladinobarber.dto.cliente.*;
import com.ladinobarber.modelo.vo.HistorialServicioVO;

import java.util.List;

public interface ClienteService {

    // ─────────────────────────────────────────
    // CRUD
    // ─────────────────────────────────────────

    String crear(CrearClienteDTO dto) throws Exception;

    InformacionClienteDTO obtenerPorId(String id) throws Exception;

    List<ItemClienteDTO> listar();

    InformacionClienteDTO actualizar(String id, EditarClienteDTO dto) throws Exception;

    void eliminar(String id) throws Exception;


    // ─────────────────────────────────────────
    // Autenticación
    // ─────────────────────────────────────────

    TokenDTO login(LoginDTO dto) throws Exception;

    void cambiarContrasena(String clienteId, CambiarContrasenaDTO dto) throws Exception;


    // ─────────────────────────────────────────
    // Recuperación de contraseña
    // ─────────────────────────────────────────

    void iniciarRecuperacionContrasena(RecuperarContrasenaDTO dto) throws Exception;

    void restablecerContrasena(RestablecerContrasenaDTO dto) throws Exception;


    // ─────────────────────────────────────────
    // Favoritos (galería de cortes)
    // ─────────────────────────────────────────

    void agregarFavorito(String clienteId, String galeriaCorteId) throws Exception;

    void eliminarFavorito(String clienteId, String galeriaCorteId) throws Exception;

    List<String> obtenerFavoritos(String clienteId) throws Exception;


    // ─────────────────────────────────────────
    // Historial de servicios
    // ─────────────────────────────────────────

    List<HistorialServicioVO> obtenerHistorialServicios(String clienteId) throws Exception;


    // ─────────────────────────────────────────
    // Validaciones
    // ─────────────────────────────────────────

    boolean existePorCorreo(String correo);

    boolean existePorTelefono(String telefono);

}