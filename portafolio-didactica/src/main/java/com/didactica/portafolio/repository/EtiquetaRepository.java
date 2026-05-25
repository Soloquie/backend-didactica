package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
    Optional<Etiqueta> findBySlug(String slug);
}
