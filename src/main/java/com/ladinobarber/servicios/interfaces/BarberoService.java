package com.ladinobarber.servicios.interfaces;

import com.ladinobarber.dto.barbero.AgregarHorarioDTO;
import com.ladinobarber.dto.barbero.CambiarEstadoHorarioDTO;
import com.ladinobarber.dto.barbero.CrearBarberoDTO;
import com.ladinobarber.dto.barbero.EditarBarberoDTO;
import com.ladinobarber.dto.barbero.InformacionBarberoDTO;
import com.ladinobarber.dto.barbero.ItemBarberoDTO;
import java.util.List;

public interface BarberoService {

    String crear(CrearBarberoDTO dto) throws Exception;

    InformacionBarberoDTO obtenerPorId(String id) throws Exception;

    List<ItemBarberoDTO> listar();

    InformacionBarberoDTO actualizar(String id, EditarBarberoDTO dto) throws Exception;

    void eliminar(String id) throws Exception;

    void agregarHorario(AgregarHorarioDTO dto) throws Exception;

    void cambiarEstadoHorario(CambiarEstadoHorarioDTO dto) throws Exception;

}
