package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.AprendizajeRequest;
import com.didactica.portafolio.dto.response.AprendizajeResponse;

public interface AprendizajeService {
    AprendizajeResponse create(Long actividadId, AprendizajeRequest request);
    AprendizajeResponse update(Long id, AprendizajeRequest request);
    void delete(Long id);
}
