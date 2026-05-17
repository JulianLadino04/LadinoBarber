package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.cita.*;
import com.ladinobarber.servicios.interfaces.CitaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @RequestBody CrearCitaDTO dto) throws Exception {
        String id = citaService.crear(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Cita registrada exitosamente", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionCitaDTO>> obtener(@PathVariable String id) throws Exception {
        InformacionCitaDTO cita = citaService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>("Cita obtenida correctamente", cita));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ItemCitaDTO>>> listar() {
        List<ItemCitaDTO> lista = citaService.listar();
        return ResponseEntity.ok(new ResponseDTO<>("Lista de citas obtenida correctamente", lista));
    }

    @PutMapping("/reprogramar")
    public ResponseEntity<ResponseDTO<InformacionCitaDTO>> reprogramar(@Valid @RequestBody ReprogramarCitaDTO dto) throws Exception {
        InformacionCitaDTO cita = citaService.reprogramar(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Cita reprogramada correctamente", cita));
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<ResponseDTO<InformacionCitaDTO>> cancelar(@PathVariable String id) throws Exception {
        InformacionCitaDTO cita = citaService.cancelar(id);
        return ResponseEntity.ok(new ResponseDTO<>("Cita cancelada correctamente", cita));
    }

}