package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.AprendizajeRequest;
import com.didactica.portafolio.dto.response.AprendizajeResponse;
import com.didactica.portafolio.service.interfaces.AprendizajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminAprendizajeController {
    private final AprendizajeService aprendizajeService;

    public AdminAprendizajeController(AprendizajeService aprendizajeService) {
        this.aprendizajeService = aprendizajeService;
    }

    @PostMapping("/actividades/{id}/aprendizajes")
    @ResponseStatus(HttpStatus.CREATED)
    public AprendizajeResponse create(@PathVariable Long id, @Valid @RequestBody AprendizajeRequest request) {
        return aprendizajeService.create(id, request);
    }

    @PutMapping("/aprendizajes/{id}")
    public AprendizajeResponse update(@PathVariable Long id, @Valid @RequestBody AprendizajeRequest request) {
        return aprendizajeService.update(id, request);
    }

    @DeleteMapping("/aprendizajes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        aprendizajeService.delete(id);
    }
}
