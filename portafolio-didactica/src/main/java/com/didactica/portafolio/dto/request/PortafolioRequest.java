package com.didactica.portafolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PortafolioRequest {
    @NotBlank
    @Size(max = 150)
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
}
