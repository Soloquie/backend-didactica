package com.didactica.portafolio.controller.publicapi;

import com.didactica.portafolio.dto.response.CategoriaResponse;
import com.didactica.portafolio.service.interfaces.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/categorias")
public class PublicCategoriaController {
    private final CategoriaService categoriaService;

    public PublicCategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaResponse> getCategorias() {
        return categoriaService.getPublicCategorias();
    }
}
