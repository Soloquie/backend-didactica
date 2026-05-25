package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.CategoriaRequest;
import com.didactica.portafolio.dto.response.CategoriaResponse;
import com.didactica.portafolio.entity.Categoria;
import com.didactica.portafolio.exception.ResourceNotFoundException;
import com.didactica.portafolio.mapper.CategoriaMapper;
import com.didactica.portafolio.repository.CategoriaRepository;
import com.didactica.portafolio.service.interfaces.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;
    private final SlugService slugService;

    public CategoriaServiceImpl(CategoriaRepository repository, CategoriaMapper mapper, SlugService slugService) {
        this.repository = repository;
        this.mapper = mapper;
        this.slugService = slugService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponse> getPublicCategorias() {
        return repository.findByActivoTrueAndEliminadoEnIsNullOrderByOrdenAscNombreAsc().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponse> getAdminCategorias() {
        return repository.findByEliminadoEnIsNullOrderByOrdenAscNombreAsc().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public CategoriaResponse create(CategoriaRequest request) {
        var categoria = new Categoria();
        mapper.update(categoria, request, buildSlug(request.getSlug(), request.getNombre()));
        return mapper.toResponse(repository.save(categoria));
    }

    @Override
    @Transactional
    public CategoriaResponse update(Long id, CategoriaRequest request) {
        var categoria = repository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        mapper.update(categoria, request, buildSlug(request.getSlug(), request.getNombre()));
        return mapper.toResponse(repository.save(categoria));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var categoria = repository.findByIdAndEliminadoEnIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        categoria.setEliminadoEn(Instant.now());
        categoria.setActivo(false);
        repository.save(categoria);
    }

    private String buildSlug(String requestSlug, String nombre) {
        return slugService.from(requestSlug == null || requestSlug.isBlank() ? nombre : requestSlug);
    }
}
