package com.didactica.portafolio.controller.publicapi;

import com.didactica.portafolio.dto.response.ActividadResponse;
import com.didactica.portafolio.service.interfaces.ActividadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/actividades")
public class PublicActividadController {
    private final ActividadService actividadService;

    public PublicActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping
    public List<ActividadResponse> getActividades() {
        return actividadService.getPublicActividades();
    }

    @GetMapping("/destacadas")
    public List<ActividadResponse> getDestacadas() {
        return actividadService.getPublicDestacadas();
    }

    @GetMapping("/{slug}")
    public ActividadResponse getBySlug(@PathVariable String slug) {
        return actividadService.getPublicBySlug(slug);
    }

    @GetMapping("/categoria/{slugCategoria}")
    public List<ActividadResponse> getByCategoria(@PathVariable String slugCategoria) {
        return actividadService.getPublicByCategoria(slugCategoria);
    }
}
