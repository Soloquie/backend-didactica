package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByEliminadoEnIsNullOrderByOrdenAscNombreAsc();

    List<Categoria> findByActivoTrueAndEliminadoEnIsNullOrderByOrdenAscNombreAsc();

    Optional<Categoria> findByIdAndEliminadoEnIsNull(Long id);

    Optional<Categoria> findBySlugAndActivoTrueAndEliminadoEnIsNull(String slug);

    boolean existsBySlugAndEliminadoEnIsNull(String slug);
}
