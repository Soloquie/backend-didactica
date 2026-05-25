package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.request.CategoriaRequest;
import com.didactica.portafolio.dto.response.CategoriaResponse;
import com.didactica.portafolio.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public CategoriaResponse toResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .slug(categoria.getSlug())
                .descripcion(categoria.getDescripcion())
                .icono(categoria.getIcono())
                .color(categoria.getColor())
                .orden(categoria.getOrden())
                .activo(categoria.getActivo())
                .creadoEn(categoria.getCreadoEn())
                .actualizadoEn(categoria.getActualizadoEn())
                .build();
    }

    public void update(Categoria categoria, CategoriaRequest request, String slug) {
        categoria.setNombre(request.getNombre());
        categoria.setSlug(slug);
        categoria.setDescripcion(request.getDescripcion());
        categoria.setIcono(request.getIcono());
        categoria.setColor(request.getColor());
        categoria.setOrden(request.getOrden());
        categoria.setActivo(request.getActivo());
    }
}
