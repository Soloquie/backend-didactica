package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.ActividadRequest;
import com.didactica.portafolio.dto.response.ActividadResponse;

import java.util.List;

public interface ActividadService {
    List<ActividadResponse> getPublicActividades();
    List<ActividadResponse> getPublicDestacadas();
    ActividadResponse getPublicBySlug(String slug);
    List<ActividadResponse> getPublicByCategoria(String slugCategoria);
    List<ActividadResponse> getAdminActividades();
    ActividadResponse getAdminById(Long id);
    ActividadResponse create(ActividadRequest request, String username);
    ActividadResponse update(Long id, ActividadRequest request, String username);
    void delete(Long id);
    ActividadResponse publicar(Long id);
    ActividadResponse archivar(Long id);
    ActividadResponse borrador(Long id);
}
