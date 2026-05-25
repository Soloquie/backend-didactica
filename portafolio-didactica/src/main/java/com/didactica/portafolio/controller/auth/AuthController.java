package com.didactica.portafolio.controller.auth;

import com.didactica.portafolio.dto.request.LoginRequest;
import com.didactica.portafolio.dto.response.AuthResponse;
import com.didactica.portafolio.service.interfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
