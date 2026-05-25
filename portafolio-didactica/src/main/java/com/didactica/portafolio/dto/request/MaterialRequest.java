package com.didactica.portafolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MaterialRequest {
    @NotBlank
    private String nombre;
    private String cantidad;
    private String descripcion;
}
