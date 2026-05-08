package com.ladinobarber.servicios.interfaces;

import com.ladinobarber.dto.producto.ActualizarStockDTO;
import com.ladinobarber.dto.producto.CrearProductoDTO;
import com.ladinobarber.dto.producto.EditarProductoDTO;
import com.ladinobarber.dto.producto.InformacionProductoDTO;
import com.ladinobarber.dto.producto.ItemProductoDTO;
import java.util.List;

public interface ProductoService {

    String crear(CrearProductoDTO dto) throws Exception;

    InformacionProductoDTO obtenerPorId(String id) throws Exception;

    List<ItemProductoDTO> listar();

    InformacionProductoDTO actualizar(String id, EditarProductoDTO dto) throws Exception;

    void eliminar(String id) throws Exception;

    void actualizarStock(ActualizarStockDTO dto) throws Exception;

}
