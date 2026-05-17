package com.ladinobarber.servicios.impl;

import com.ladinobarber.config.JWTUtils;
import com.ladinobarber.dto.TokenDTO;
import com.ladinobarber.dto.cliente.*;
import com.ladinobarber.modelo.documentos.Cliente;
import com.ladinobarber.modelo.enums.EstadoPromocion;
import com.ladinobarber.modelo.enums.Rol;
import com.ladinobarber.modelo.vo.HistorialServicioVO;
import com.ladinobarber.repositorios.ClienteRepository;
import com.ladinobarber.servicios.interfaces.ClienteService;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final long CODIGO_RECUPERACION_EXPIRACION_MINUTOS = 15;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final ClienteRepository clienteRepository;
    private final JavaMailSender javaMailSender;
    private final boolean mailSenderAvailable;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JWTUtils jwtUtils;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              ObjectProvider<JavaMailSender> javaMailSenderProvider,
                              JWTUtils jwtUtils) {

        this.clienteRepository = clienteRepository;
        this.javaMailSender = javaMailSenderProvider.getIfAvailable();
        this.mailSenderAvailable = this.javaMailSender != null;
        this.jwtUtils = jwtUtils;
    }

    // ─────────────────────────────────────────
    // CRUD
    // ─────────────────────────────────────────

    @Override
    public String crear(CrearClienteDTO dto) throws Exception {

        if (clienteRepository.existsByCorreo(dto.correo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        if (clienteRepository.existsByTelefono(dto.telefono())) {
            throw new Exception("El teléfono ya está registrado");
        }

        Cliente cliente = new Cliente();

        cliente.setId(UUID.randomUUID().toString());
        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setCorreo(dto.correo());

        // 🔐 ENCRIPTAR CONTRASEÑA
        cliente.setContrasena(passwordEncoder.encode(dto.password()));

        cliente.setRol(Rol.CLIENTE);
        cliente.setFavoritosIds(new ArrayList<>());
        cliente.setHistorialServicios(new ArrayList<>());

        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setFechaActualizacion(LocalDateTime.now());

        Cliente guardado = clienteRepository.save(cliente);

        return guardado.getId();
    }

    @Override
    public InformacionClienteDTO obtenerPorId(String id) throws Exception {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        int cortesAcumulados = cliente.getHistorialServicios() != null
                ? cliente.getHistorialServicios().size()
                : 0;

        EstadoPromocion estadoPromocion = cliente.getPromocion() != null
                ? EstadoPromocion.ACTIVA
                : EstadoPromocion.INACTIVA;

        return new InformacionClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cortesAcumulados,
                estadoPromocion,
                0,
                null,
                cliente.getFechaRegistro(),
                true
        );
    }

    @Override
    public List<ItemClienteDTO> listar() {

        return clienteRepository.findAll().stream()
                .map(c -> new ItemClienteDTO(
                        c.getId(),
                        c.getNombre(),
                        c.getCorreo(),
                        c.getTelefono(),
                        true
                ))
                .collect(Collectors.toList());
    }

    @Override
    public InformacionClienteDTO actualizar(String id, EditarClienteDTO dto) throws Exception {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setFechaActualizacion(LocalDateTime.now());

        Cliente actualizado = clienteRepository.save(cliente);

        return obtenerPorId(actualizado.getId());
    }

    @Override
    public void eliminar(String id) throws Exception {

        if (!clienteRepository.existsById(id)) {
            throw new Exception("Cliente no encontrado");
        }

        clienteRepository.deleteById(id);
    }

    // ─────────────────────────────────────────
    // LOGIN
    // ─────────────────────────────────────────

    @Override
    public TokenDTO login(LoginDTO dto) throws Exception {

        Cliente cliente = clienteRepository.findByCorreo(dto.correo())
                .orElseThrow(() -> new Exception("Correo o contraseña incorrectos"));

        if (!passwordEncoder.matches(dto.contrasena(), cliente.getContrasena())) {
            throw new Exception("Correo o contraseña incorrectos");
        }

        if (cliente.getRol() == null) {
            throw new Exception("El usuario no tiene rol asignado");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", cliente.getId());
        claims.put("nombre", cliente.getNombre());
        claims.put("rol", cliente.getRol().name());

        String token = jwtUtils.generarToken(cliente.getCorreo(), claims);

        return new TokenDTO(token);
    }

    // ─────────────────────────────────────────
    // CAMBIAR CONTRASEÑA
    // ─────────────────────────────────────────

    @Override
    public void cambiarContrasena(String clienteId, CambiarContrasenaDTO dto) throws Exception {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        // Validar contraseña actual
        if (!passwordEncoder.matches(dto.passwordActual(), cliente.getContrasena())) {
            throw new Exception("La contraseña actual es incorrecta");
        }

        // Encriptar nueva contraseña
        cliente.setContrasena(passwordEncoder.encode(dto.passwordNueva()));
        cliente.setFechaActualizacion(LocalDateTime.now());

        clienteRepository.save(cliente);
    }

    // ─────────────────────────────────────────
    // RECUPERACIÓN CONTRASEÑA
    // ─────────────────────────────────────────

    @Override
    public void iniciarRecuperacionContrasena(RecuperarContrasenaDTO dto) throws Exception {

        Cliente cliente = clienteRepository.findByCorreo(dto.correo())
                .orElseThrow(() -> new Exception("Correo no registrado"));

        String codigo = generarCodigoRecuperacion();

        cliente.setCodigoRecuperacion(codigo);
        cliente.setFechaExpiracionCodigoRecuperacion(
                LocalDateTime.now().plusMinutes(CODIGO_RECUPERACION_EXPIRACION_MINUTOS)
        );

        clienteRepository.save(cliente);

        enviarCodigoRecuperacion(dto.correo(), codigo);
    }

    @Override
    public void restablecerContrasena(RestablecerContrasenaDTO dto) throws Exception {

        Cliente cliente = clienteRepository.findByCodigoRecuperacion(dto.codigoRecuperacion())
                .orElseThrow(() -> new Exception("Código de recuperación inválido"));

        if (cliente.getFechaExpiracionCodigoRecuperacion() == null ||
                cliente.getFechaExpiracionCodigoRecuperacion().isBefore(LocalDateTime.now())) {
            throw new Exception("El código ha expirado");
        }

        if (!dto.nuevaContrasena().equals(dto.confirmarContrasena())) {
            throw new Exception("Las contraseñas no coinciden");
        }

        cliente.setContrasena(passwordEncoder.encode(dto.nuevaContrasena()));
        cliente.setCodigoRecuperacion(null);
        cliente.setFechaExpiracionCodigoRecuperacion(null);

        clienteRepository.save(cliente);
    }

    private String generarCodigoRecuperacion() {
        int numero = 100000 + RANDOM.nextInt(900000);
        return String.valueOf(numero);
    }

    private void enviarCodigoRecuperacion(String correo, String codigo) throws Exception {

        try {

            if (mailSenderAvailable) {

                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(correo);
                message.setSubject("Recuperación de contraseña - Ladino Barber");
                message.setText("Tu código de recuperación es: " + codigo);

                javaMailSender.send(message);

            } else {

                System.out.println("[RECUPERACION] Código para " + correo + ": " + codigo);

            }

        } catch (Exception e) {

            throw new Exception("No fue posible enviar el correo");

        }
    }

    // ─────────────────────────────────────────
    // FAVORITOS
    // ─────────────────────────────────────────

    @Override
    public void agregarFavorito(String clienteId, String galeriaCorteId) throws Exception {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        if (cliente.getFavoritosIds() == null) {
            cliente.setFavoritosIds(new ArrayList<>());
        }

        if (!cliente.getFavoritosIds().contains(galeriaCorteId)) {
            cliente.getFavoritosIds().add(galeriaCorteId);
        }

        clienteRepository.save(cliente);
    }

    @Override
    public void eliminarFavorito(String clienteId, String galeriaCorteId) throws Exception {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        cliente.getFavoritosIds().remove(galeriaCorteId);

        clienteRepository.save(cliente);
    }

    @Override
    public List<String> obtenerFavoritos(String clienteId) throws Exception {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        return cliente.getFavoritosIds();
    }

    // ─────────────────────────────────────────
    // HISTORIAL
    // ─────────────────────────────────────────

    @Override
    public List<HistorialServicioVO> obtenerHistorialServicios(String clienteId) throws Exception {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        return cliente.getHistorialServicios();
    }

    // ─────────────────────────────────────────
    // VALIDACIONES
    // ─────────────────────────────────────────

    @Override
    public boolean existePorCorreo(String correo) {
        return clienteRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existePorTelefono(String telefono) {
        return clienteRepository.existsByTelefono(telefono);
    }
}