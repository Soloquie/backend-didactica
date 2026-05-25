package com.didactica.portafolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequest {
    @NotBlank
    @Size(max = 120)
    private String nombre;
    private String slug;
    private String descripcion;
    private String icono;
    private String color;
    private Integer orden;
    private Boolean activo;
}
