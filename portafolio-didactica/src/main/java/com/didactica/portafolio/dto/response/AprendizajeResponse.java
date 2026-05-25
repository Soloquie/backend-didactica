package com.didactica.portafolio.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AprendizajeResponse {
    private Long id;
    private String descripcion;
    private Integer orden;
}
