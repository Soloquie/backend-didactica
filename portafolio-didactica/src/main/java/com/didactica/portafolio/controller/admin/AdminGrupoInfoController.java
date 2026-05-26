package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.GrupoInfoRequest;
import com.didactica.portafolio.dto.response.GrupoInfoResponse;
import com.didactica.portafolio.service.interfaces.GrupoInfoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/grupo-info")
public class AdminGrupoInfoController {
    private final GrupoInfoService grupoInfoService;

    public AdminGrupoInfoController(GrupoInfoService grupoInfoService) {
        this.grupoInfoService = grupoInfoService;
    }

    @GetMapping
    public GrupoInfoResponse getGrupoInfo() {
        return grupoInfoService.getAdminGrupoInfo();
    }

    @PutMapping
    public GrupoInfoResponse update(@Valid @RequestBody GrupoInfoRequest request) {
        return grupoInfoService.update(request);
    }

    @PostMapping(value = "/imagen", consumes = {"multipart/form-data"})
    public GrupoInfoResponse uploadImage(@RequestPart("archivo") MultipartFile archivo) {
        return grupoInfoService.uploadImage(archivo);
    }
}
