package com.ladinobarber.servicios.impl;

import com.ladinobarber.dto.galeria.*;
import com.ladinobarber.modelo.documentos.Barbero;
import com.ladinobarber.modelo.documentos.GaleriaCorte;
import com.ladinobarber.repositorios.BarberoRepository;
import com.ladinobarber.repositorios.GaleriaCorteRepository;
import com.ladinobarber.servicios.interfaces.GaleriaCorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GaleriaCorteServiceImpl implements GaleriaCorteService {

    @Autowired
    private GaleriaCorteRepository galeriaCorteRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    @Override
    public String crear(CrearGaleriaCorteDTO dto) throws Exception {
        Barbero barbero = barberoRepository.findById(dto.barberoId()).orElseThrow(() -> new Exception("Barbero no encontrado"));

        GaleriaCorte galeriaCorte = new GaleriaCorte();
        galeriaCorte.setImagenUrl(dto.imagenUrl());
        galeriaCorte.setDescripcion(dto.descripcion());
        galeriaCorte.setEtiquetas(dto.etiquetas());
        galeriaCorte.setBarberoId(dto.barberoId());
        galeriaCorte.setNombreBarbero(barbero.getNombre());
        galeriaCorte.setCitaId(dto.citaId());
        galeriaCorte.setTotalFavoritos(0);
        galeriaCorte.setVisible(true);

        GaleriaCorte guardada = galeriaCorteRepository.save(galeriaCorte);
        return guardada.getId();
    }

    @Override
    public InformacionGaleriaCorteDTO obtenerPorId(String id) throws Exception {
        GaleriaCorte galeriaCorte = galeriaCorteRepository.findById(id).orElseThrow(() -> new Exception("GaleriaCorte no encontrada"));
        return new InformacionGaleriaCorteDTO(
            galeriaCorte.getId(),
            galeriaCorte.getImagenUrl(),
            galeriaCorte.getDescripcion(),
            galeriaCorte.getEtiquetas(),
            galeriaCorte.getBarberoId(),
            galeriaCorte.getNombreBarbero(),
            galeriaCorte.getTotalFavoritos(),
            galeriaCorte.isVisible(),
            galeriaCorte.getFechaPublicacion()
        );
    }

    @Override
    public List<ItemGaleriaCorteDTO> listar() {
        return galeriaCorteRepository.findByVisibleTrueOrderByFechaPublicacionDesc().stream()
            .map(g -> new ItemGaleriaCorteDTO(
                g.getId(),
                g.getImagenUrl(),
                g.getDescripcion(),
                g.getNombreBarbero(),
                g.getEtiquetas(),
                g.getTotalFavoritos(),
                g.getFechaPublicacion()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(String id) throws Exception {
        if (!galeriaCorteRepository.existsById(id)) {
            throw new Exception("GaleriaCorte no encontrada");
        }
        galeriaCorteRepository.deleteById(id);
    }

}
