package com.didactica.portafolio.dto.request;

import com.didactica.portafolio.enums.EstadoActividad;
import com.didactica.portafolio.enums.TipoActividad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActividadRequest {
    @NotNull
    private Long categoriaId;

    @NotBlank
    private String nombre;

    private String slug;

    @NotNull
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
}
