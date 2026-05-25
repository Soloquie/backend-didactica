package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.EvidenciaRequest;
import com.didactica.portafolio.dto.response.EvidenciaResponse;
import com.didactica.portafolio.service.interfaces.EvidenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminEvidenciaController {
    private final EvidenciaService evidenciaService;

    public AdminEvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @PostMapping(value = "/actividades/{id}/evidencias", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public EvidenciaResponse createMultipart(@PathVariable Long id,
                                             @Valid @ModelAttribute EvidenciaRequest request,
                                             @RequestPart(value = "archivo", required = false) MultipartFile archivo) {
        return evidenciaService.create(id, request, archivo);
    }

    @PostMapping("/actividades/{id}/evidencias")
    @ResponseStatus(HttpStatus.CREATED)
    public EvidenciaResponse createJson(@PathVariable Long id, @Valid @RequestBody EvidenciaRequest request) {
        return evidenciaService.create(id, request, null);
    }

    @DeleteMapping("/evidencias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        evidenciaService.delete(id);
    }
}
