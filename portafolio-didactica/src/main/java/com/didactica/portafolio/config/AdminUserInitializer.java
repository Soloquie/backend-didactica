package com.didactica.portafolio.config;

import com.didactica.portafolio.entity.Usuario;
import com.didactica.portafolio.enums.RolUsuario;
import com.didactica.portafolio.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminUserInitializer implements ApplicationRunner {
    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "12345";

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (usuarioRepository.existsByCorreo(ADMIN_EMAIL)) {
            return;
        }

        var admin = Usuario.builder()
                .nombre("Administrador")
                .correo(ADMIN_EMAIL)
                .passwordHash(passwordEncoder.encode(ADMIN_PASSWORD))
                .rol(RolUsuario.ADMIN)
                .activo(true)
                .build();

        usuarioRepository.save(admin);
    }
}
