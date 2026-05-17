package com.ladinobarber.servicios.impl;

import com.ladinobarber.dto.producto.*;
import com.ladinobarber.modelo.documentos.Producto;
import com.ladinobarber.repositorios.ProductoRepository;
import com.ladinobarber.servicios.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public String crear(CrearProductoDTO dto) throws Exception {
        if (productoRepository.existsByNombre(dto.nombre())) {
            throw new Exception("Ya existe un producto con ese nombre");
        }
        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setMarca(dto.marca());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setImagenUrl(dto.imagenUrl());
        producto.setActivo(true);
        Producto guardado = productoRepository.save(producto);
        return guardado.getId();
    }

    @Override
    public InformacionProductoDTO obtenerPorId(String id) throws Exception {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
        return new InformacionProductoDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getMarca(),
            producto.getPrecio(),
            producto.getStock(),
            producto.getImagenUrl(),
            producto.isActivo()
        );
    }

    @Override
    public List<ItemProductoDTO> listar() {
        return productoRepository.findByActivoTrue().stream()
            .map(p -> new ItemProductoDTO(
                p.getId(),
                p.getNombre(),
                p.getMarca(),
                p.getPrecio(),
                p.getStock(),
                p.getImagenUrl()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public InformacionProductoDTO actualizar(String id, EditarProductoDTO dto) throws Exception {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setMarca(dto.marca());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setImagenUrl(dto.imagenUrl());
        Producto actualizado = productoRepository.save(producto);
        return new InformacionProductoDTO(
            actualizado.getId(),
            actualizado.getNombre(),
            actualizado.getDescripcion(),
            actualizado.getMarca(),
            actualizado.getPrecio(),
            actualizado.getStock(),
            actualizado.getImagenUrl(),
            actualizado.isActivo()
        );
    }

    @Override
    public void eliminar(String id) throws Exception {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
        producto.setActivo(false);
        productoRepository.save(producto);
    }

}
