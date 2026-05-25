package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.MaterialRequest;
import com.didactica.portafolio.dto.response.MaterialResponse;

public interface MaterialService {
    MaterialResponse create(Long actividadId, MaterialRequest request);
    MaterialResponse update(Long id, MaterialRequest request);
    void delete(Long id);
}
