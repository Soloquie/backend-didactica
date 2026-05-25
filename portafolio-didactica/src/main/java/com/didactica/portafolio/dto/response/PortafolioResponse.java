package com.didactica.portafolio.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PortafolioResponse {
    private Long id;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private String materia;
    private String docente;
    private String institucion;
    private String periodoAcademico;
    private String imagenPortadaUrl;
    private String colorPrincipal;
    private String colorSecundario;
    private Boolean activo;
    private Instant creadoEn;
    private Instant actualizadoEn;
}
