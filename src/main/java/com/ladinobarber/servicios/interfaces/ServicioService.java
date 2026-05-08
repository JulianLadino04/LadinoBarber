package com.ladinobarber.servicios.interfaces;

import com.ladinobarber.dto.servicio.CrearServicioDTO;
import com.ladinobarber.dto.servicio.EditarServicioDTO;
import com.ladinobarber.dto.servicio.InformacionServicioDTO;
import com.ladinobarber.dto.servicio.ItemServicioDTO;
import java.util.List;

public interface ServicioService {

    String crear(CrearServicioDTO dto) throws Exception;

    InformacionServicioDTO obtenerPorId(String id) throws Exception;

    List<ItemServicioDTO> listar();

    InformacionServicioDTO actualizar(String id, EditarServicioDTO dto) throws Exception;

    void eliminar(String id) throws Exception;

}
