package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.response.MaterialResponse;
import com.didactica.portafolio.entity.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    public MaterialResponse toResponse(Material material) {
        return MaterialResponse.builder()
                .id(material.getId())
                .nombre(material.getNombre())
                .cantidad(material.getCantidad())
                .descripcion(material.getDescripcion())
                .build();
    }
}
