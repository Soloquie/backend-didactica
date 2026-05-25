package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.PortafolioRequest;
import com.didactica.portafolio.dto.response.PortafolioResponse;
import com.didactica.portafolio.exception.ResourceNotFoundException;
import com.didactica.portafolio.mapper.PortafolioMapper;
import com.didactica.portafolio.repository.PortafolioRepository;
import com.didactica.portafolio.service.interfaces.PortafolioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortafolioServiceImpl implements PortafolioService {
    private final PortafolioRepository repository;
    private final PortafolioMapper mapper;

    public PortafolioServiceImpl(PortafolioRepository repository, PortafolioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PortafolioResponse getPublicPortafolio() {
        return repository.findFirstByActivoTrueAndEliminadoEnIsNullOrderByIdAsc()
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Portafolio no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortafolioResponse> getAdminPortafolios() {
        return repository.findByEliminadoEnIsNullOrderByIdAsc().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public PortafolioResponse update(Long id, PortafolioRequest request) {
        var portafolio = repository.findById(id)
                .filter(p -> p.getEliminadoEn() == null)
                .orElseThrow(() -> new ResourceNotFoundException("Portafolio no encontrado"));
        mapper.update(portafolio, request);
        return mapper.toResponse(repository.save(portafolio));
    }
}
