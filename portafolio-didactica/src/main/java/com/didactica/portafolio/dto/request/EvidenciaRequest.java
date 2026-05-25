package com.didactica.portafolio.dto.request;

import com.didactica.portafolio.enums.TipoEvidencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvidenciaRequest {
    @NotBlank
    private String titulo;
    private String descripcion;

    @NotNull
    private TipoEvidencia tipo;

    private String urlArchivo;
    private String nombreArchivo;
    private String mimeType;
    private Long pesoBytes;
    private Integer orden;
    private Boolean esPortada;
}
