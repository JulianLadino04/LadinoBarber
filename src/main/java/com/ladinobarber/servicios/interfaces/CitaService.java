package com.ladinobarber.servicios.interfaces;

import com.ladinobarber.dto.cita.CrearCitaDTO;
import com.ladinobarber.dto.cita.GestionarCitaDTO;
import com.ladinobarber.dto.cita.InformacionCitaDTO;
import com.ladinobarber.dto.cita.ItemCitaDTO;
import com.ladinobarber.dto.cita.ReprogramarCitaDTO;
import java.util.List;

public interface CitaService {

    String crear(CrearCitaDTO dto) throws Exception;

    InformacionCitaDTO obtenerPorId(String id) throws Exception;

    List<ItemCitaDTO> listar();

    InformacionCitaDTO reprogramar(ReprogramarCitaDTO dto) throws Exception;

    void gestionar(GestionarCitaDTO dto) throws Exception;

    void eliminar(String id) throws Exception;

    InformacionCitaDTO cancelar(String id) throws Exception;

}
