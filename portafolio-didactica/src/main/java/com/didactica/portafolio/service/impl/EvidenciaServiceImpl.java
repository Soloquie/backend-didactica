package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.EvidenciaRequest;
import com.didactica.portafolio.dto.response.EvidenciaResponse;
import com.didactica.portafolio.entity.Evidencia;
import com.didactica.portafolio.exception.BadRequestException;
import com.didactica.portafolio.exception.ResourceNotFoundException;
import com.didactica.portafolio.mapper.EvidenciaMapper;
import com.didactica.portafolio.repository.ActividadRepository;
import com.didactica.portafolio.repository.EvidenciaRepository;
import com.didactica.portafolio.service.interfaces.CloudinaryService;
import com.didactica.portafolio.service.interfaces.EvidenciaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Service
public class EvidenciaServiceImpl implements EvidenciaService {
    private final EvidenciaRepository evidenciaRepository;
    private final ActividadRepository actividadRepository;
    private final CloudinaryService cloudinaryService;
    private final EvidenciaMapper mapper;

    public EvidenciaServiceImpl(EvidenciaRepository evidenciaRepository, ActividadRepository actividadRepository,
                                CloudinaryService cloudinaryService, EvidenciaMapper mapper) {
        this.evidenciaRepository = evidenciaRepository;
        this.actividadRepository = actividadRepository;
        this.cloudinaryService = cloudinaryService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public EvidenciaResponse create(Long actividadId, EvidenciaRequest request, MultipartFile archivo) {
        var actividad = actividadRepository.findByIdAndEliminadoEnIsNull(actividadId)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));

        var evidencia = new Evidencia();
        evidencia.setActividad(actividad);
        evidencia.setTitulo(request.getTitulo());
        evidencia.setDescripcion(request.getDescripcion());
        evidencia.setTipo(request.getTipo());
        evidencia.setOrden(request.getOrden());
        evidencia.setEsPortada(Boolean.TRUE.equals(request.getEsPortada()));

        if (archivo != null && !archivo.isEmpty()) {
            var upload = cloudinaryService.upload(archivo, "portafolio-didactica/evidencias");
            evidencia.setUrlArchivo(upload.getUrl());
            evidencia.setNombreArchivo(upload.getOriginalFilename());
            evidencia.setMimeType(upload.getContentType());
            evidencia.setPesoBytes(upload.getSize());
        } else {
            if (request.getUrlArchivo() == null || request.getUrlArchivo().isBlank()) {
                throw new BadRequestException("Debe enviar un archivo o una URL de evidencia");
            }
            evidencia.setUrlArchivo(request.getUrlArchivo());
            evidencia.setNombreArchivo(request.getNombreArchivo());
            evidencia.setMimeType(request.getMimeType());
            evidencia.setPesoBytes(request.getPesoBytes());
        }

        if (Boolean.TRUE.equals(evidencia.getEsPortada())) {
            evidenciaRepository.findByActividadIdAndEsPortadaTrueAndEliminadoEnIsNull(actividadId)
                    .forEach(actual -> {
                        actual.setEsPortada(false);
                        evidenciaRepository.save(actual);
                    });
            actividad.setPortadaUrl(evidencia.getUrlArchivo());
            actividadRepository.save(actividad);
        }

        return mapper.toResponse(evidenciaRepository.save(evidencia));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var evidencia = evidenciaRepository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evidencia no encontrada"));
        evidencia.setEliminadoEn(Instant.now());
        evidenciaRepository.save(evidencia);
    }
}
