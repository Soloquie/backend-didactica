package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.EvidenciaRequest;
import com.didactica.portafolio.dto.response.EvidenciaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EvidenciaService {
    EvidenciaResponse create(Long actividadId, EvidenciaRequest request, MultipartFile archivo);
    void delete(Long id);
}
