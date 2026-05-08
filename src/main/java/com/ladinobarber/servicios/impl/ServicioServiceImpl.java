package com.ladinobarber.servicios.impl;

import com.ladinobarber.dto.servicio.*;
import com.ladinobarber.modelo.documentos.Servicio;
import com.ladinobarber.repositorios.ServicioRepository;
import com.ladinobarber.servicios.interfaces.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public String crear(CrearServicioDTO dto) throws Exception {
        if (servicioRepository.existsByNombre(dto.nombre())) {
            throw new Exception("Ya existe un servicio con ese nombre");
        }
        Servicio servicio = new Servicio();
        servicio.setNombre(dto.nombre());
        servicio.setDescripcion(dto.descripcion());
        servicio.setTipo(dto.tipo());
        servicio.setPrecio(dto.precio());
        servicio.setDuracionMinutos(dto.duracionMinutos());
        servicio.setImagenUrl(dto.imagenUrl());
        servicio.setActivo(true);
        Servicio guardado = servicioRepository.save(servicio);
        return guardado.getId();
    }

    @Override
    public InformacionServicioDTO obtenerPorId(String id) throws Exception {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new Exception("Servicio no encontrado"));
        return new InformacionServicioDTO(
            servicio.getId(),
            servicio.getNombre(),
            servicio.getDescripcion(),
            servicio.getTipo(),
            servicio.getPrecio(),
            servicio.getDuracionMinutos(),
            servicio.getImagenUrl(),
            servicio.isActivo(),
            servicio.getFechaCreacion()
        );
    }

    @Override
    public List<ItemServicioDTO> listar() {
        return servicioRepository.findByActivoTrue().stream()
            .map(s -> new ItemServicioDTO(
                s.getId(),
                s.getNombre(),
                s.getTipo(),
                s.getPrecio(),
                s.getDuracionMinutos(),
                s.getImagenUrl()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public InformacionServicioDTO actualizar(String id, EditarServicioDTO dto) throws Exception {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new Exception("Servicio no encontrado"));
        servicio.setNombre(dto.nombre());
        servicio.setDescripcion(dto.descripcion());
        servicio.setTipo(dto.tipo());
        servicio.setPrecio(dto.precio());
        servicio.setDuracionMinutos(dto.duracionMinutos());
        servicio.setImagenUrl(dto.imagenUrl());
        Servicio actualizado = servicioRepository.save(servicio);
        return new InformacionServicioDTO(
            actualizado.getId(),
            actualizado.getNombre(),
            actualizado.getDescripcion(),
            actualizado.getTipo(),
            actualizado.getPrecio(),
            actualizado.getDuracionMinutos(),
            actualizado.getImagenUrl(),
            actualizado.isActivo(),
            actualizado.getFechaCreacion()
        );
    }

    @Override
    public void eliminar(String id) throws Exception {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new Exception("Servicio no encontrado"));
        servicio.setActivo(false);
        servicioRepository.save(servicio);
    }

}
