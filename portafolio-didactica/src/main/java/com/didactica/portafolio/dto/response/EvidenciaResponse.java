package com.didactica.portafolio.dto.response;

import com.didactica.portafolio.enums.TipoEvidencia;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EvidenciaResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private TipoEvidencia tipo;
    private String urlArchivo;
    private String nombreArchivo;
    private String mimeType;
    private Long pesoBytes;
    private Integer orden;
    private Boolean esPortada;
}
