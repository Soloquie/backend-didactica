package com.didactica.portafolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AprendizajeRequest {
    @NotBlank
    private String descripcion;
    private Integer orden;
}
