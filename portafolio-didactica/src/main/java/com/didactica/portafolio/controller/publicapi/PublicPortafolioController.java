package com.didactica.portafolio.controller.publicapi;

import com.didactica.portafolio.dto.response.PortafolioResponse;
import com.didactica.portafolio.service.interfaces.PortafolioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/portafolio")
public class PublicPortafolioController {
    private final PortafolioService portafolioService;

    public PublicPortafolioController(PortafolioService portafolioService) {
        this.portafolioService = portafolioService;
    }

    @GetMapping
    public PortafolioResponse getPortafolio() {
        return portafolioService.getPublicPortafolio();
    }
}
