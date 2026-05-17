package com.ladinobarber.servicios.impl;

import com.ladinobarber.dto.cita.*;
import com.ladinobarber.modelo.documentos.Barbero;
import com.ladinobarber.modelo.documentos.Cita;
import com.ladinobarber.modelo.documentos.Cliente;
import com.ladinobarber.modelo.documentos.Servicio;
import com.ladinobarber.modelo.enums.EstadoCita;
import com.ladinobarber.repositorios.BarberoRepository;
import com.ladinobarber.repositorios.CitaRepository;
import com.ladinobarber.repositorios.ClienteRepository;
import com.ladinobarber.repositorios.ServicioRepository;
import com.ladinobarber.servicios.interfaces.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public String crear(CrearCitaDTO dto) throws Exception {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new Exception("Cliente no encontrado"));
        Barbero barbero = barberoRepository.findById(dto.barberoId()).orElseThrow(() -> new Exception("Barbero no encontrado"));
        Servicio servicio = servicioRepository.findById(dto.servicioId()).orElseThrow(() -> new Exception("Servicio no encontrado"));

        if (citaRepository.existsByBarberoIdAndFechaAndHoraInicio(dto.barberoId(), dto.fecha(), dto.horaInicio())) {
            throw new Exception("El barbero ya tiene una cita en ese horario");
        }

        LocalTime horaFin = dto.horaInicio().plusMinutes(servicio.getDuracionMinutos());

        Cita cita = new Cita();
        cita.setClienteId(dto.clienteId());
        cita.setNombreCliente(cliente.getNombre());
        cita.setTelefonoCliente(cliente.getTelefono());
        cita.setBarberoId(dto.barberoId());
        cita.setNombreBarbero(barbero.getNombre());
        cita.setServicioId(dto.servicioId());
        cita.setNombreServicio(servicio.getNombre());
        cita.setFecha(dto.fecha());
        cita.setHoraInicio(dto.horaInicio());
        cita.setHoraFin(horaFin);
        cita.setPrecioBase(servicio.getPrecio());
        cita.setPrecioFinal(servicio.getPrecio());
        cita.setBonoAplicado(false);
        cita.setEstado(EstadoCita.PENDIENTE);
        cita.setObservaciones("");
        cita.setConfirmacionEnviada(false);
        cita.setRecordatorioEnviado(false);

        Cita guardada = citaRepository.save(cita);
        return guardada.getId();
    }

    @Override
    public InformacionCitaDTO obtenerPorId(String id) throws Exception {

        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new Exception("Cita no encontrada"));

        return new InformacionCitaDTO(
                cita.getId(),
                cita.getClienteId(),
                cita.getNombreCliente(),
                cita.getTelefonoCliente(),
                cita.getBarberoId(),
                cita.getNombreBarbero(),
                cita.getServicioId(),
                cita.getNombreServicio(),
                cita.getFecha(),
                cita.getHoraInicio(),
                cita.getHoraFin(),
                cita.getPrecioBase(),
                cita.getPrecioFinal(),
                cita.isBonoAplicado(),
                cita.getEstado(),
                cita.getObservaciones(),
                cita.getCitaOriginalId(),
                cita.isConfirmacionEnviada(),
                cita.isRecordatorioEnviado(),
                cita.getFechaCreacion()
        );
    }

    @Override
    public List<ItemCitaDTO> listar() {

        return citaRepository.findAll().stream()
                .map(c -> new ItemCitaDTO(
                        c.getId(),
                        c.getNombreCliente(),
                        c.getNombreBarbero(),
                        c.getNombreServicio(),
                        c.getFecha(),
                        c.getHoraInicio(),
                        c.getHoraFin(),
                        c.getPrecioFinal(),
                        c.getEstado()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public InformacionCitaDTO reprogramar(ReprogramarCitaDTO dto) throws Exception {

        Cita cita = citaRepository.findById(dto.citaId())
                .orElseThrow(() -> new Exception("Cita no encontrada"));

        Servicio servicio = servicioRepository.findById(cita.getServicioId())
                .orElseThrow(() -> new Exception("Servicio no encontrado"));

        if (citaRepository.existsByBarberoIdAndFechaAndHoraInicio(
                cita.getBarberoId(),
                dto.nuevaFecha(),
                dto.nuevaHoraInicio())) {

            throw new Exception("El barbero ya tiene una cita en ese horario");
        }

        // Guardar referencia de la cita original
        cita.setCitaOriginalId(cita.getId());

        LocalTime nuevaHoraFin = dto.nuevaHoraInicio()
                .plusMinutes(servicio.getDuracionMinutos());

        cita.setFecha(dto.nuevaFecha());
        cita.setHoraInicio(dto.nuevaHoraInicio());
        cita.setHoraFin(nuevaHoraFin);
        cita.setEstado(EstadoCita.PENDIENTE);
        cita.setObservaciones(dto.observaciones());
        cita.setConfirmacionEnviada(false);
        cita.setRecordatorioEnviado(false);

        Cita actualizada = citaRepository.save(cita);

        return new InformacionCitaDTO(
                actualizada.getId(),
                actualizada.getClienteId(),
                actualizada.getNombreCliente(),
                actualizada.getTelefonoCliente(),
                actualizada.getBarberoId(),
                actualizada.getNombreBarbero(),
                actualizada.getServicioId(),
                actualizada.getNombreServicio(),
                actualizada.getFecha(),
                actualizada.getHoraInicio(),
                actualizada.getHoraFin(),
                actualizada.getPrecioBase(),
                actualizada.getPrecioFinal(),
                actualizada.isBonoAplicado(),
                actualizada.getEstado(),
                actualizada.getObservaciones(),
                actualizada.getCitaOriginalId(),
                actualizada.isConfirmacionEnviada(),
                actualizada.isRecordatorioEnviado(),
                actualizada.getFechaCreacion()
        );
    }

    @Override
    public void gestionar(GestionarCitaDTO dto) throws Exception {
        Cita cita = citaRepository.findById(dto.citaId()).orElseThrow(() -> new Exception("Cita no encontrada"));
        // Asumir que se confirma, finaliza o cancela basado en lógica externa, por ahora set observaciones
        cita.setObservaciones(dto.observaciones());
        citaRepository.save(cita);
    }

    @Override
    public void eliminar(String id) throws Exception {
        if (!citaRepository.existsById(id)) {
            throw new Exception("Cita no encontrada");
        }
        citaRepository.deleteById(id);
    }

    @Override
    public InformacionCitaDTO cancelar(String id) throws Exception {

        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new Exception("Cita no encontrada"));

        cita.setEstado(EstadoCita.CANCELADA);

        Cita cancelada = citaRepository.save(cita);

        return new InformacionCitaDTO(
                cancelada.getId(),
                cancelada.getClienteId(),
                cancelada.getNombreCliente(),
                cancelada.getTelefonoCliente(),
                cancelada.getBarberoId(),
                cancelada.getNombreBarbero(),
                cancelada.getServicioId(),
                cancelada.getNombreServicio(),
                cancelada.getFecha(),
                cancelada.getHoraInicio(),
                cancelada.getHoraFin(),
                cancelada.getPrecioBase(),
                cancelada.getPrecioFinal(),
                cancelada.isBonoAplicado(),
                cancelada.getEstado(),
                cancelada.getObservaciones(),
                cancelada.getCitaOriginalId(),
                cancelada.isConfirmacionEnviada(),
                cancelada.isRecordatorioEnviado(),
                cancelada.getFechaCreacion()
        );
    }

}
