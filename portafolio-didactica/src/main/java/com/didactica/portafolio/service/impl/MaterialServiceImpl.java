package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.MaterialRequest;
import com.didactica.portafolio.dto.response.MaterialResponse;
import com.didactica.portafolio.entity.Material;
import com.didactica.portafolio.exception.ResourceNotFoundException;
import com.didactica.portafolio.mapper.MaterialMapper;
import com.didactica.portafolio.repository.ActividadRepository;
import com.didactica.portafolio.repository.MaterialRepository;
import com.didactica.portafolio.service.interfaces.MaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final ActividadRepository actividadRepository;
    private final MaterialMapper mapper;

    public MaterialServiceImpl(MaterialRepository materialRepository, ActividadRepository actividadRepository,
                               MaterialMapper mapper) {
        this.materialRepository = materialRepository;
        this.actividadRepository = actividadRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public MaterialResponse create(Long actividadId, MaterialRequest request) {
        var actividad = actividadRepository.findByIdAndEliminadoEnIsNull(actividadId)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
        var material = Material.builder()
                .actividad(actividad)
                .nombre(request.getNombre())
                .cantidad(request.getCantidad())
                .descripcion(request.getDescripcion())
                .build();
        return mapper.toResponse(materialRepository.save(material));
    }

    @Override
    @Transactional
    public MaterialResponse update(Long id, MaterialRequest request) {
        var material = materialRepository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));
        material.setNombre(request.getNombre());
        material.setCantidad(request.getCantidad());
        material.setDescripcion(request.getDescripcion());
        return mapper.toResponse(materialRepository.save(material));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var material = materialRepository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));
        material.setEliminadoEn(Instant.now());
        materialRepository.save(material);
    }
}
