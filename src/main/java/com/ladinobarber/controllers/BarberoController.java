package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.barbero.*;
import com.ladinobarber.servicios.interfaces.BarberoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barberos")
public class BarberoController {

    private final BarberoService barberoService;

    public BarberoController(BarberoService barberoService) {
        this.barberoService = barberoService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @RequestBody CrearBarberoDTO dto) throws Exception {
        String id = barberoService.crear(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Barbero registrado exitosamente", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionBarberoDTO>> obtener(@PathVariable String id) throws Exception {
        InformacionBarberoDTO barbero = barberoService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>("Barbero obtenido correctamente", barbero));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ItemBarberoDTO>>> listar() {
        List<ItemBarberoDTO> lista = barberoService.listar();
        return ResponseEntity.ok(new ResponseDTO<>("Lista de barberos obtenida correctamente", lista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionBarberoDTO>> actualizar(@PathVariable String id,
                                            @Valid @RequestBody EditarBarberoDTO dto) throws Exception {
        InformacionBarberoDTO barbero = barberoService.actualizar(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Barbero actualizado correctamente", barbero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable String id) throws Exception {
        barberoService.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>("Barbero eliminado correctamente", null));
    }

}