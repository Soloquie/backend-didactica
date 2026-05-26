package com.didactica.portafolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class GrupoInfoRequest {
    @NotBlank
    @Size(max = 150)
    private String titulo;

    private String descripcion;
    private String imagenUrl;
    private List<String> integrantes;
    private Boolean activo;
}
