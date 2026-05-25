package com.didactica.portafolio.mapper;

import com.didactica.portafolio.dto.response.AprendizajeResponse;
import com.didactica.portafolio.entity.Aprendizaje;
import org.springframework.stereotype.Component;

@Component
public class AprendizajeMapper {
    public AprendizajeResponse toResponse(Aprendizaje aprendizaje) {
        return AprendizajeResponse.builder()
                .id(aprendizaje.getId())
                .descripcion(aprendizaje.getDescripcion())
                .orden(aprendizaje.getOrden())
                .build();
    }
}
