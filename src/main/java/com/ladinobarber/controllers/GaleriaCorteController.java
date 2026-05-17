package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.galeria.*;
import com.ladinobarber.servicios.interfaces.GaleriaCorteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galeria")
public class GaleriaCorteController {

    private final GaleriaCorteService galeriaService;

    public GaleriaCorteController(GaleriaCorteService galeriaService) {
        this.galeriaService = galeriaService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @RequestBody CrearGaleriaCorteDTO dto) throws Exception {
        String id = galeriaService.crear(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Corte registrado en galería exitosamente", id));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ItemGaleriaCorteDTO>>> listar() {
        List<ItemGaleriaCorteDTO> lista = galeriaService.listar();
        return ResponseEntity.ok(new ResponseDTO<>("Lista de cortes obtenida correctamente", lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionGaleriaCorteDTO>> obtener(@PathVariable String id) throws Exception {
        InformacionGaleriaCorteDTO corte = galeriaService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>("Corte obtenido correctamente", corte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable String id) throws Exception {
        galeriaService.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>("Corte eliminado correctamente", null));
    }

}