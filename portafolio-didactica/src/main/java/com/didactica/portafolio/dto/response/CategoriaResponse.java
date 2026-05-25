package com.didactica.portafolio.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CategoriaResponse {
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private String icono;
    private String color;
    private Integer orden;
    private Boolean activo;
    private Instant creadoEn;
    private Instant actualizadoEn;
}
