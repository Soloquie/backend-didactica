package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.CategoriaRequest;
import com.didactica.portafolio.dto.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> getPublicCategorias();
    List<CategoriaResponse> getAdminCategorias();
    CategoriaResponse create(CategoriaRequest request);
    CategoriaResponse update(Long id, CategoriaRequest request);
    void delete(Long id);
}
