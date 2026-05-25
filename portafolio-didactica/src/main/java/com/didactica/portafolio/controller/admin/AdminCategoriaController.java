package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.CategoriaRequest;
import com.didactica.portafolio.dto.response.CategoriaResponse;
import com.didactica.portafolio.service.interfaces.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categorias")
public class AdminCategoriaController {
    private final CategoriaService categoriaService;

    public AdminCategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaResponse> getCategorias() {
        return categoriaService.getAdminCategorias();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponse create(@Valid @RequestBody CategoriaRequest request) {
        return categoriaService.create(request);
    }

    @PutMapping("/{id}")
    public CategoriaResponse update(@PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {
        return categoriaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoriaService.delete(id);
    }
}
