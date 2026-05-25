package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndActivoTrueAndEliminadoEnIsNull(String correo);

    boolean existsByCorreo(String correo);
}
