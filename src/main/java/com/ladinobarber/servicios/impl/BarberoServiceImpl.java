package com.ladinobarber.servicios.impl;

import com.ladinobarber.dto.barbero.*;
import com.ladinobarber.modelo.documentos.Barbero;
import com.ladinobarber.modelo.enums.DiaSemana;
import com.ladinobarber.modelo.enums.Rol;
import com.ladinobarber.modelo.vo.HorarioDisponible;
import com.ladinobarber.repositorios.BarberoRepository;
import com.ladinobarber.servicios.interfaces.BarberoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberoServiceImpl implements BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;

    @Override
    public String crear(CrearBarberoDTO dto) throws Exception {
        if (barberoRepository.existsByCorreo(dto.correo())) {
            throw new Exception("El correo ya está registrado");
        }
        Barbero barbero = new Barbero();
        barbero.setNombre(dto.nombre());
        barbero.setTelefono(dto.telefono());
        barbero.setCorreo(dto.correo());
        barbero.setContrasena(dto.contrasena());
        barbero.setRol(Rol.BARBERO);
        barbero.setDescripcion(dto.descripcion());
        barbero.setFotoUrl(dto.fotoUrl());
        barbero.setActivo(true);
        barbero.setHorarios(List.of());
        barbero.setCodigoRecuperacion(null);
        barbero.setFechaExpiracionCodigoRecuperacion(null);
        Barbero guardado = barberoRepository.save(barbero);
        return guardado.getId();
    }

    @Override
    public InformacionBarberoDTO obtenerPorId(String id) throws Exception {
        Barbero barbero = barberoRepository.findById(id).orElseThrow(() -> new Exception("Barbero no encontrado"));
        List<HorarioDisponibleDTO> horarios = barbero.getHorarios().stream()
            .map(h -> new HorarioDisponibleDTO(h.getDia(), h.getHoraInicio(), h.getHoraFin(), h.isHabilitado()))
            .collect(Collectors.toList());
        return new InformacionBarberoDTO(
            barbero.getId(),
            barbero.getNombre(),
            barbero.getTelefono(),
            barbero.getCorreo(),
            barbero.getDescripcion(),
            barbero.getFotoUrl(),
            barbero.isActivo(),
            horarios,
            barbero.getFechaRegistro()
        );
    }

    @Override
    public List<ItemBarberoDTO> listar() {
        return barberoRepository.findByActivoTrue().stream()
            .map(b -> new ItemBarberoDTO(
                b.getId(),
                b.getNombre(),
                b.getCorreo(),
                b.getTelefono(),
                b.isActivo()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public InformacionBarberoDTO actualizar(String id, EditarBarberoDTO dto) throws Exception {

        Barbero barbero = barberoRepository.findById(id)
                .orElseThrow(() -> new Exception("Barbero no encontrado"));

        barbero.setNombre(dto.nombre());
        barbero.setDescripcion(dto.descripcion());
        barbero.setFotoUrl(dto.fotoUrl());

        Barbero actualizado = barberoRepository.save(barbero);

        List<HorarioDisponibleDTO> horarios = actualizado.getHorarios().stream()
                .map(h -> new HorarioDisponibleDTO(
                        h.getDia(),
                        h.getHoraInicio(),
                        h.getHoraFin(),
                        h.isHabilitado()
                ))
                .collect(Collectors.toList());

        return new InformacionBarberoDTO(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getTelefono(),
                actualizado.getCorreo(),
                actualizado.getDescripcion(),
                actualizado.getFotoUrl(),
                actualizado.isActivo(),
                horarios,
                actualizado.getFechaRegistro()
        );
    }

    @Override
    public void eliminar(String id) throws Exception {
        Barbero barbero = barberoRepository.findById(id).orElseThrow(() -> new Exception("Barbero no encontrado"));
        barbero.setActivo(false);
        barberoRepository.save(barbero);
    }

    @Override
    public void agregarHorario(AgregarHorarioDTO dto) throws Exception {
        Barbero barbero = barberoRepository.findById(dto.barberoId()).orElseThrow(() -> new Exception("Barbero no encontrado"));
        List<HorarioDisponible> horarios = barbero.getHorarios();
        HorarioDisponible existente = horarios.stream().filter(h -> h.getDia() == dto.dia()).findFirst().orElse(null);
        if (existente != null) {
            existente.setHoraInicio(dto.horaInicio());
            existente.setHoraFin(dto.horaFin());
            existente.setHabilitado(true);
        } else {
            HorarioDisponible nuevo = new HorarioDisponible();
            nuevo.setDia(dto.dia());
            nuevo.setHoraInicio(dto.horaInicio());
            nuevo.setHoraFin(dto.horaFin());
            nuevo.setHabilitado(true);
            horarios.add(nuevo);
        }
        barbero.setHorarios(horarios);
        barberoRepository.save(barbero);
    }

    @Override
    public void cambiarEstadoHorario(CambiarEstadoHorarioDTO dto) throws Exception {
        Barbero barbero = barberoRepository.findById(dto.barberoId()).orElseThrow(() -> new Exception("Barbero no encontrado"));
        List<HorarioDisponible> horarios = barbero.getHorarios();
        HorarioDisponible horario = horarios.stream().filter(h -> h.getDia() == dto.dia()).findFirst().orElseThrow(() -> new Exception("Horario no encontrado"));
        horario.setHabilitado(dto.habilitado());
        barberoRepository.save(barbero);
    }

}
