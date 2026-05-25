package com.didactica.portafolio.controller.admin;

import com.didactica.portafolio.dto.request.MaterialRequest;
import com.didactica.portafolio.dto.response.MaterialResponse;
import com.didactica.portafolio.service.interfaces.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminMaterialController {
    private final MaterialService materialService;

    public AdminMaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/actividades/{id}/materiales")
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponse create(@PathVariable Long id, @Valid @RequestBody MaterialRequest request) {
        return materialService.create(id, request);
    }

    @PutMapping("/materiales/{id}")
    public MaterialResponse update(@PathVariable Long id, @Valid @RequestBody MaterialRequest request) {
        return materialService.update(id, request);
    }

    @DeleteMapping("/materiales/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        materialService.delete(id);
    }
}
