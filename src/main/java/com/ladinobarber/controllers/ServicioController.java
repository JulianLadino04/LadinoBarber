package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.servicio.*;
import com.ladinobarber.servicios.interfaces.ServicioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @RequestBody CrearServicioDTO dto) throws Exception {
        String id = servicioService.crear(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Servicio registrado exitosamente", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionServicioDTO>> obtener(@PathVariable String id) throws Exception {
        InformacionServicioDTO servicio = servicioService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>("Servicio obtenido correctamente", servicio));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ItemServicioDTO>>> listar() {
        List<ItemServicioDTO> lista = servicioService.listar();
        return ResponseEntity.ok(new ResponseDTO<>("Lista de servicios obtenida correctamente", lista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionServicioDTO>> actualizar(@PathVariable String id,
                                             @Valid @RequestBody EditarServicioDTO dto) throws Exception {
        InformacionServicioDTO servicio = servicioService.actualizar(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Servicio actualizado correctamente", servicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable String id) throws Exception {
        servicioService.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>("Servicio eliminado correctamente", null));
    }

}