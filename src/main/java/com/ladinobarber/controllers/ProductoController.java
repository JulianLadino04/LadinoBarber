package com.ladinobarber.controllers;

import com.ladinobarber.dto.ResponseDTO;
import com.ladinobarber.dto.producto.*;
import com.ladinobarber.servicios.interfaces.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @RequestBody CrearProductoDTO dto) throws Exception {
        String id = productoService.crear(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Producto registrado exitosamente", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionProductoDTO>> obtener(@PathVariable String id) throws Exception {
        InformacionProductoDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>("Producto obtenido correctamente", producto));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ItemProductoDTO>>> listar() {
        List<ItemProductoDTO> lista = productoService.listar();
        return ResponseEntity.ok(new ResponseDTO<>("Lista de productos obtenida correctamente", lista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<InformacionProductoDTO>> actualizar(@PathVariable String id,
                                             @Valid @RequestBody EditarProductoDTO dto) throws Exception {
        InformacionProductoDTO producto = productoService.actualizar(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Producto actualizado correctamente", producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable String id) throws Exception {
        productoService.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>("Producto eliminado correctamente", null));
    }

}