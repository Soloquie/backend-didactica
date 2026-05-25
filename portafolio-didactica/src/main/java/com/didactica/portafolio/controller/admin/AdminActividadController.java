package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.ActividadRequest;
import com.didactica.portafolio.dto.response.ActividadResponse;
import com.didactica.portafolio.service.interfaces.ActividadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/actividades")
public class AdminActividadController {
    private final ActividadService actividadService;

    public AdminActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping
    public List<ActividadResponse> getActividades() {
        return actividadService.getAdminActividades();
    }

    @GetMapping("/{id}")
    public ActividadResponse getById(@PathVariable Long id) {
        return actividadService.getAdminById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActividadResponse create(@Valid @RequestBody ActividadRequest request, Authentication authentication) {
        return actividadService.create(request, authentication.getName());
    }

    @PutMapping("/{id}")
    public ActividadResponse update(@PathVariable Long id, @Valid @RequestBody ActividadRequest request,
                                    Authentication authentication) {
        return actividadService.update(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        actividadService.delete(id);
    }

    @PatchMapping("/{id}/publicar")
    public ActividadResponse publicar(@PathVariable Long id) {
        return actividadService.publicar(id);
    }

    @PatchMapping("/{id}/archivar")
    public ActividadResponse archivar(@PathVariable Long id) {
        return actividadService.archivar(id);
    }

    @PatchMapping("/{id}/borrador")
    public ActividadResponse borrador(@PathVariable Long id) {
        return actividadService.borrador(id);
    }
}
