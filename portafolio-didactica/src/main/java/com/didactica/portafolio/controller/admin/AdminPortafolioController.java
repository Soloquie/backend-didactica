package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.PortafolioRequest;
import com.didactica.portafolio.dto.response.PortafolioResponse;
import com.didactica.portafolio.service.interfaces.PortafolioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/portafolio")
public class AdminPortafolioController {
    private final PortafolioService portafolioService;

    public AdminPortafolioController(PortafolioService portafolioService) {
        this.portafolioService = portafolioService;
    }

    @GetMapping
    public List<PortafolioResponse> getPortafolios() {
        return portafolioService.getAdminPortafolios();
    }

    @PutMapping("/{id}")
    public PortafolioResponse update(@PathVariable Long id, @Valid @RequestBody PortafolioRequest request) {
        return portafolioService.update(id, request);
    }
}
