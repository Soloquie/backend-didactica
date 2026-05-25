package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.request.ActividadRequest;
import com.didactica.portafolio.dto.response.ActividadResponse;
import com.didactica.portafolio.entity.Actividad;
import com.didactica.portafolio.entity.Evidencia;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ActividadMapper {
    private final CategoriaMapper categoriaMapper;
    private final EvidenciaMapper evidenciaMapper;
    private final AprendizajeMapper aprendizajeMapper;
    private final MaterialMapper materialMapper;

    public ActividadMapper(CategoriaMapper categoriaMapper, EvidenciaMapper evidenciaMapper,
                           AprendizajeMapper aprendizajeMapper, MaterialMapper materialMapper) {
        this.categoriaMapper = categoriaMapper;
        this.evidenciaMapper = evidenciaMapper;
        this.aprendizajeMapper = aprendizajeMapper;
        this.materialMapper = materialMapper;
    }

    public ActividadResponse toResponse(Actividad actividad, boolean includeChildren) {
        var builder = ActividadResponse.builder()
                .id(actividad.getId())
                .categoria(categoriaMapper.toResponse(actividad.getCategoria()))
                .nombre(actividad.getNombre())
                .slug(actividad.getSlug())
                .tipo(actividad.getTipo())
                .estado(actividad.getEstado())
                .resumen(actividad.getResumen())
                .descripcion(actividad.getDescripcion())
                .objetivoDidactico(actividad.getObjetivoDidactico())
                .metodologia(actividad.getMetodologia())
                .publicoObjetivo(actividad.getPublicoObjetivo())
                .lugar(actividad.getLugar())
                .fechaRealizacion(actividad.getFechaRealizacion())
                .portadaUrl(actividad.getPortadaUrl())
                .reflexionFinal(actividad.getReflexionFinal())
                .destacado(actividad.getDestacado())
                .orden(actividad.getOrden())
                .creadoEn(actividad.getCreadoEn())
                .actualizadoEn(actividad.getActualizadoEn())
                .publicadoEn(actividad.getPublicadoEn());

        if (includeChildren) {
            builder.evidencias(actividad.getEvidencias().stream()
                    .filter(e -> e.getEliminadoEn() == null)
                    .sorted(Comparator.comparing((Evidencia e) -> e.getOrden() == null ? 0 : e.getOrden())
                            .thenComparing(e -> e.getId() == null ? 0 : e.getId()))
                    .map(evidenciaMapper::toResponse)
                    .toList());
            builder.aprendizajes(actividad.getAprendizajes().stream()
                    .filter(a -> a.getEliminadoEn() == null)
                    .sorted(Comparator.comparing(a -> a.getOrden() == null ? 0 : a.getOrden()))
                    .map(aprendizajeMapper::toResponse)
                    .toList());
            builder.materiales(actividad.getMateriales().stream()
                    .filter(m -> m.getEliminadoEn() == null)
                    .map(materialMapper::toResponse)
                    .toList());
        }
        return builder.build();
    }

    public void update(Actividad actividad, ActividadRequest request, String slug) {
        actividad.setNombre(request.getNombre());
        actividad.setSlug(slug);
        actividad.setTipo(request.getTipo());
        actividad.setEstado(request.getEstado());
        actividad.setResumen(request.getResumen());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setObjetivoDidactico(request.getObjetivoDidactico());
        actividad.setMetodologia(request.getMetodologia());
        actividad.setPublicoObjetivo(request.getPublicoObjetivo());
        actividad.setLugar(request.getLugar());
        actividad.setFechaRealizacion(request.getFechaRealizacion());
        actividad.setPortadaUrl(request.getPortadaUrl());
        actividad.setReflexionFinal(request.getReflexionFinal());
        actividad.setDestacado(request.getDestacado());
        actividad.setOrden(request.getOrden());
    }
}
