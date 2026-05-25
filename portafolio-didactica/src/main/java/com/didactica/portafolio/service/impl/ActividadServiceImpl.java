package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.ActividadRequest;
import com.didactica.portafolio.dto.response.ActividadResponse;
import com.didactica.portafolio.entity.Actividad;
import com.didactica.portafolio.enums.EstadoActividad;
import com.didactica.portafolio.exception.ResourceNotFoundException;
import com.didactica.portafolio.mapper.ActividadMapper;
import com.didactica.portafolio.repository.ActividadRepository;
import com.didactica.portafolio.repository.CategoriaRepository;
import com.didactica.portafolio.repository.UsuarioRepository;
import com.didactica.portafolio.service.interfaces.ActividadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ActividadServiceImpl implements ActividadService {
    private final ActividadRepository actividadRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ActividadMapper mapper;
    private final SlugService slugService;

    public ActividadServiceImpl(ActividadRepository actividadRepository, CategoriaRepository categoriaRepository,
                                UsuarioRepository usuarioRepository, ActividadMapper mapper, SlugService slugService) {
        this.actividadRepository = actividadRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.slugService = slugService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadResponse> getPublicActividades() {
        return actividadRepository.findByEstadoAndEliminadoEnIsNullOrderByOrdenAscPublicadoEnDesc(EstadoActividad.PUBLICADO)
                .stream().map(a -> mapper.toResponse(a, false)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadResponse> getPublicDestacadas() {
        return actividadRepository.findByEstadoAndDestacadoTrueAndEliminadoEnIsNullOrderByOrdenAscPublicadoEnDesc(EstadoActividad.PUBLICADO)
                .stream().map(a -> mapper.toResponse(a, false)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ActividadResponse getPublicBySlug(String slug) {
        return actividadRepository.findBySlugAndEstadoAndEliminadoEnIsNull(slug, EstadoActividad.PUBLICADO)
                .map(a -> mapper.toResponse(a, true))
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadResponse> getPublicByCategoria(String slugCategoria) {
        return actividadRepository.findByCategoriaSlugAndEstadoAndEliminadoEnIsNullOrderByOrdenAscPublicadoEnDesc(slugCategoria, EstadoActividad.PUBLICADO)
                .stream().map(a -> mapper.toResponse(a, false)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadResponse> getAdminActividades() {
        return actividadRepository.findByEliminadoEnIsNullOrderByOrdenAscCreadoEnDesc()
                .stream().map(a -> mapper.toResponse(a, false)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ActividadResponse getAdminById(Long id) {
        return mapper.toResponse(findActividad(id), true);
    }

    @Override
    @Transactional
    public ActividadResponse create(ActividadRequest request, String username) {
        var actividad = new Actividad();
        var usuario = usuarioRepository.findByCorreoAndActivoTrueAndEliminadoEnIsNull(username).orElse(null);
        actividad.setCreadoPor(usuario);
        actividad.setActualizadoPor(usuario);
        fill(actividad, request);
        return mapper.toResponse(actividadRepository.save(actividad), true);
    }

    @Override
    @Transactional
    public ActividadResponse update(Long id, ActividadRequest request, String username) {
        var actividad = findActividad(id);
        var usuario = usuarioRepository.findByCorreoAndActivoTrueAndEliminadoEnIsNull(username).orElse(null);
        actividad.setActualizadoPor(usuario);
        fill(actividad, request);
        return mapper.toResponse(actividadRepository.save(actividad), true);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var actividad = findActividad(id);
        actividad.setEliminadoEn(Instant.now());
        actividadRepository.save(actividad);
    }

    @Override
    @Transactional
    public ActividadResponse publicar(Long id) {
        var actividad = findActividad(id);
        actividad.setEstado(EstadoActividad.PUBLICADO);
        actividad.setPublicadoEn(Instant.now());
        return mapper.toResponse(actividadRepository.save(actividad), true);
    }

    @Override
    @Transactional
    public ActividadResponse archivar(Long id) {
        var actividad = findActividad(id);
        actividad.setEstado(EstadoActividad.ARCHIVADO);
        return mapper.toResponse(actividadRepository.save(actividad), true);
    }

    @Override
    @Transactional
    public ActividadResponse borrador(Long id) {
        var actividad = findActividad(id);
        actividad.setEstado(EstadoActividad.BORRADOR);
        actividad.setPublicadoEn(null);
        return mapper.toResponse(actividadRepository.save(actividad), true);
    }

    private void fill(Actividad actividad, ActividadRequest request) {
        var categoria = categoriaRepository.findByIdAndEliminadoEnIsNull(request.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        actividad.setCategoria(categoria);
        var slug = slugService.from(request.getSlug() == null || request.getSlug().isBlank() ? request.getNombre() : request.getSlug());
        mapper.update(actividad, request, slug);
        if (actividad.getEstado() == EstadoActividad.PUBLICADO && actividad.getPublicadoEn() == null) {
            actividad.setPublicadoEn(Instant.now());
        }
    }

    private Actividad findActividad(Long id) {
        return actividadRepository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
    }
}
