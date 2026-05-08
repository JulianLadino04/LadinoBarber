package com.ladinobarber.servicios.interfaces;

import com.ladinobarber.dto.galeria.CrearGaleriaCorteDTO;
import com.ladinobarber.dto.galeria.InformacionGaleriaCorteDTO;
import com.ladinobarber.dto.galeria.ItemGaleriaCorteDTO;
import java.util.List;

public interface GaleriaCorteService {

    String crear(CrearGaleriaCorteDTO dto) throws Exception;

    InformacionGaleriaCorteDTO obtenerPorId(String id) throws Exception;

    List<ItemGaleriaCorteDTO> listar();

    void eliminar(String id) throws Exception;

}
