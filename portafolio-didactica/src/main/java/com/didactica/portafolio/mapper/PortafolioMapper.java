package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.request.PortafolioRequest;
import com.didactica.portafolio.dto.response.PortafolioResponse;
import com.didactica.portafolio.entity.Portafolio;
import org.springframework.stereotype.Component;

@Component
public class PortafolioMapper {
    public PortafolioResponse toResponse(Portafolio portafolio) {
        return PortafolioResponse.builder()
                .id(portafolio.getId())
                .titulo(portafolio.getTitulo())
                .subtitulo(portafolio.getSubtitulo())
                .descripcion(portafolio.getDescripcion())
                .materia(portafolio.getMateria())
                .docente(portafolio.getDocente())
                .institucion(portafolio.getInstitucion())
                .periodoAcademico(portafolio.getPeriodoAcademico())
                .imagenPortadaUrl(portafolio.getImagenPortadaUrl())
                .colorPrincipal(portafolio.getColorPrincipal())
                .colorSecundario(portafolio.getColorSecundario())
                .activo(portafolio.getActivo())
                .creadoEn(portafolio.getCreadoEn())
                .actualizadoEn(portafolio.getActualizadoEn())
                .build();
    }

    public void update(Portafolio portafolio, PortafolioRequest request) {
        portafolio.setTitulo(request.getTitulo());
        portafolio.setSubtitulo(request.getSubtitulo());
        portafolio.setDescripcion(request.getDescripcion());
        portafolio.setMateria(request.getMateria());
        portafolio.setDocente(request.getDocente());
        portafolio.setInstitucion(request.getInstitucion());
        portafolio.setPeriodoAcademico(request.getPeriodoAcademico());
        portafolio.setImagenPortadaUrl(request.getImagenPortadaUrl());
        portafolio.setColorPrincipal(request.getColorPrincipal());
        portafolio.setColorSecundario(request.getColorSecundario());
        portafolio.setActivo(request.getActivo());
    }
}
