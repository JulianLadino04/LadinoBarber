package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.cliente.*;
import com.ladinobarber.modelo.vo.HistorialServicioVO;
import com.ladinobarber.servicios.interfaces.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionClienteDTO>> obtener(@PathVariable String id) throws Exception {
        InformacionClienteDTO cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>("Cliente obtenido correctamente", cliente));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ItemClienteDTO>>> listar() {
        List<ItemClienteDTO> lista = clienteService.listar();
        return ResponseEntity.ok(new ResponseDTO<>("Lista de clientes obtenida correctamente", lista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionClienteDTO>> actualizar(@PathVariable String id,
                                            @Valid @RequestBody EditarClienteDTO dto) throws Exception {
        InformacionClienteDTO cliente = clienteService.actualizar(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Cliente actualizado correctamente", cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable String id) throws Exception {
        clienteService.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>("Cliente eliminado correctamente", null));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ResponseDTO<Void>> cambiarContrasena(@PathVariable String id,
                                  @Valid @RequestBody CambiarContrasenaDTO dto) throws Exception {
        clienteService.cambiarContrasena(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Contraseña actualizada correctamente", null));
    }

    @PostMapping("/{id}/favoritos/{corteId}")
    public ResponseEntity<ResponseDTO<Void>> agregarFavorito(@PathVariable String id,
                                @PathVariable String corteId) throws Exception {
        clienteService.agregarFavorito(id, corteId);
        return ResponseEntity.ok(new ResponseDTO<>("Favorito agregado correctamente", null));
    }

    @DeleteMapping("/{id}/favoritos/{corteId}")
    public ResponseEntity<ResponseDTO<Void>> eliminarFavorito(@PathVariable String id,
                                 @PathVariable String corteId) throws Exception {
        clienteService.eliminarFavorito(id, corteId);
        return ResponseEntity.ok(new ResponseDTO<>("Favorito eliminado correctamente", null));
    }

    @GetMapping("/{id}/favoritos")
    public ResponseEntity<ResponseDTO<List<String>>> favoritos(@PathVariable String id) throws Exception {
        List<String> favoritos = clienteService.obtenerFavoritos(id);
        return ResponseEntity.ok(new ResponseDTO<>("Favoritos obtenidos correctamente", favoritos));
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<ResponseDTO<List<HistorialServicioVO>>> historial(@PathVariable String id) throws Exception {
        List<HistorialServicioVO> historial = clienteService.obtenerHistorialServicios(id);
        return ResponseEntity.ok(new ResponseDTO<>("Historial obtenido correctamente", historial));
    }

}