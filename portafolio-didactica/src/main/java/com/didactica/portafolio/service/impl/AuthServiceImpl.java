package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.LoginRequest;
import com.didactica.portafolio.dto.response.AuthResponse;
import com.didactica.portafolio.exception.UnauthorizedException;
import com.didactica.portafolio.repository.UsuarioRepository;
import com.didactica.portafolio.security.JwtService;
import com.didactica.portafolio.security.SecurityUser;
import com.didactica.portafolio.service.interfaces.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        var usuario = usuarioRepository.findByCorreoAndActivoTrueAndEliminadoEnIsNull(request.getCorreo())
                .orElseThrow(() -> new UnauthorizedException("Credenciales invalidas"));
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new UnauthorizedException("Credenciales invalidas");
        }
        var token = jwtService.generateToken(new SecurityUser(usuario));
        return AuthResponse.builder()
                .token(token)
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .rol(usuario.getRol())
                .build();
    }
}
