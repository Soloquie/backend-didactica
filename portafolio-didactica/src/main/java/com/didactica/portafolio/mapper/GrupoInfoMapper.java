package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.request.GrupoInfoRequest;
import com.didactica.portafolio.dto.response.GrupoInfoResponse;
import com.didactica.portafolio.entity.GrupoInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GrupoInfoMapper {
    public GrupoInfoResponse toResponse(GrupoInfo grupoInfo) {
        return GrupoInfoResponse.builder()
                .id(grupoInfo.getId())
                .titulo(grupoInfo.getTitulo())
                .descripcion(grupoInfo.getDescripcion())
                .imagenUrl(grupoInfo.getImagenUrl())
                .integrantes(new ArrayList<>(grupoInfo.getIntegrantes()))
                .activo(grupoInfo.getActivo())
                .creadoEn(grupoInfo.getCreadoEn())
                .actualizadoEn(grupoInfo.getActualizadoEn())
                .build();
    }

    public void update(GrupoInfo grupoInfo, GrupoInfoRequest request) {
        grupoInfo.setTitulo(request.getTitulo());
        grupoInfo.setDescripcion(request.getDescripcion());
        grupoInfo.setImagenUrl(request.getImagenUrl());
        grupoInfo.setActivo(request.getActivo());
        grupoInfo.setIntegrantes(request.getIntegrantes() == null ? new ArrayList<>() : new ArrayList<>(request.getIntegrantes()));
    }
}
