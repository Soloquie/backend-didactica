package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.AprendizajeRequest;
import com.didactica.portafolio.dto.response.AprendizajeResponse;
import com.didactica.portafolio.entity.Aprendizaje;
import com.didactica.portafolio.exception.ResourceNotFoundException;
import com.didactica.portafolio.mapper.AprendizajeMapper;
import com.didactica.portafolio.repository.ActividadRepository;
import com.didactica.portafolio.repository.AprendizajeRepository;
import com.didactica.portafolio.service.interfaces.AprendizajeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AprendizajeServiceImpl implements AprendizajeService {
    private final AprendizajeRepository aprendizajeRepository;
    private final ActividadRepository actividadRepository;
    private final AprendizajeMapper mapper;

    public AprendizajeServiceImpl(AprendizajeRepository aprendizajeRepository, ActividadRepository actividadRepository,
                                  AprendizajeMapper mapper) {
        this.aprendizajeRepository = aprendizajeRepository;
        this.actividadRepository = actividadRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AprendizajeResponse create(Long actividadId, AprendizajeRequest request) {
        var actividad = actividadRepository.findByIdAndEliminadoEnIsNull(actividadId)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
        var aprendizaje = Aprendizaje.builder()
                .actividad(actividad)
                .descripcion(request.getDescripcion())
                .orden(request.getOrden())
                .build();
        return mapper.toResponse(aprendizajeRepository.save(aprendizaje));
    }

    @Override
    @Transactional
    public AprendizajeResponse update(Long id, AprendizajeRequest request) {
        var aprendizaje = aprendizajeRepository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aprendizaje no encontrado"));
        aprendizaje.setDescripcion(request.getDescripcion());
        aprendizaje.setOrden(request.getOrden());
        return mapper.toResponse(aprendizajeRepository.save(aprendizaje));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var aprendizaje = aprendizajeRepository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aprendizaje no encontrado"));
        aprendizaje.setEliminadoEn(Instant.now());
        aprendizajeRepository.save(aprendizaje);
    }
}
