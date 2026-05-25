package com.didactica.portafolio.dto.response;

import com.didactica.portafolio.enums.EstadoActividad;
import com.didactica.portafolio.enums.TipoActividad;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ActividadResponse {
    private Long id;
    private CategoriaResponse categoria;
    private String nombre;
    private String slug;
    private TipoActividad tipo;
    private EstadoActividad estado;
    private String resumen;
    private String descripcion;
    private String objetivoDidactico;
    private String metodologia;
    private String publicoObjetivo;
    private String lugar;
    private LocalDate fechaRealizacion;
    private String portadaUrl;
    private String reflexionFinal;
    private Boolean destacado;
    private Integer orden;
    private Instant creadoEn;
    private Instant actualizadoEn;
    private Instant publicadoEn;
    private List<EvidenciaResponse> evidencias;
    private List<AprendizajeResponse> aprendizajes;
    private List<MaterialResponse> materiales;
}
