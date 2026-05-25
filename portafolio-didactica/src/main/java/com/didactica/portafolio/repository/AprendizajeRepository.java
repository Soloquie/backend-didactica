package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Aprendizaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AprendizajeRepository extends JpaRepository<Aprendizaje, Long> {
    List<Aprendizaje> findByActividadIdAndEliminadoEnIsNullOrderByOrdenAscIdAsc(Long actividadId);

    Optional<Aprendizaje> findByIdAndEliminadoEnIsNull(Long id);
}
