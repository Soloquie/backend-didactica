package com.didactica.portafolio.controller.publicapi;

import com.didactica.portafolio.dto.response.GrupoInfoResponse;
import com.didactica.portafolio.service.interfaces.GrupoInfoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/grupo-info")
public class PublicGrupoInfoController {
    private final GrupoInfoService grupoInfoService;

    public PublicGrupoInfoController(GrupoInfoService grupoInfoService) {
        this.grupoInfoService = grupoInfoService;
    }

    @GetMapping
    public GrupoInfoResponse getGrupoInfo() {
        return grupoInfoService.getPublicGrupoInfo();
    }
}
