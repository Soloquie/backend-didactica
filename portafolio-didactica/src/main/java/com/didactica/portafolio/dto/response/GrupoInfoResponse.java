package com.didactica.portafolio.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class GrupoInfoResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private String imagenUrl;
    private List<GrupoIntegranteResponse> integrantes;
    private Boolean activo;
    private Instant creadoEn;
    private Instant actualizadoEn;

    @Data
    @Builder
    public static class GrupoIntegranteResponse {
        private String nombre;
        private String descripcion;
        private String imagenUrl;
    }
}
