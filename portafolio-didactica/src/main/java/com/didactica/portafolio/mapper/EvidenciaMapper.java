package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.response.EvidenciaResponse;
import com.didactica.portafolio.entity.Evidencia;
import org.springframework.stereotype.Component;

@Component
public class EvidenciaMapper {
    public EvidenciaResponse toResponse(Evidencia evidencia) {
        return EvidenciaResponse.builder()
                .id(evidencia.getId())
                .titulo(evidencia.getTitulo())
                .descripcion(evidencia.getDescripcion())
                .tipo(evidencia.getTipo())
                .urlArchivo(evidencia.getUrlArchivo())
                .nombreArchivo(evidencia.getNombreArchivo())
                .mimeType(evidencia.getMimeType())
                .pesoBytes(evidencia.getPesoBytes())
                .orden(evidencia.getOrden())
                .esPortada(evidencia.getEsPortada())
                .build();
    }
}
