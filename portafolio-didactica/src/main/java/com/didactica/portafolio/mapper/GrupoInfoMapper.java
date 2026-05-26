package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.request.GrupoInfoRequest;
import com.didactica.portafolio.dto.response.GrupoInfoResponse;
import com.didactica.portafolio.entity.GrupoIntegrante;
import com.didactica.portafolio.entity.GrupoInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class GrupoInfoMapper {
    public GrupoInfoResponse toResponse(GrupoInfo grupoInfo) {
        return GrupoInfoResponse.builder()
                .id(grupoInfo.getId())
                .titulo(grupoInfo.getTitulo())
                .descripcion(grupoInfo.getDescripcion())
                .imagenUrl(grupoInfo.getImagenUrl())
                .integrantes(grupoInfo.getIntegrantes().stream()
                        .map(this::toIntegranteResponse)
                        .toList())
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
        grupoInfo.setIntegrantes(request.getIntegrantes() == null ? new ArrayList<>() : request.getIntegrantes().stream()
                .filter(integrante -> integrante.getNombre() != null && !integrante.getNombre().isBlank())
                .map(this::toIntegrante)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    private GrupoInfoResponse.GrupoIntegranteResponse toIntegranteResponse(GrupoIntegrante integrante) {
        return GrupoInfoResponse.GrupoIntegranteResponse.builder()
                .nombre(integrante.getNombre())
                .descripcion(integrante.getDescripcion())
                .imagenUrl(integrante.getImagenUrl())
                .build();
    }

    private GrupoIntegrante toIntegrante(GrupoInfoRequest.GrupoIntegranteRequest request) {
        return GrupoIntegrante.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .imagenUrl(request.getImagenUrl())
                .build();
    }
}
