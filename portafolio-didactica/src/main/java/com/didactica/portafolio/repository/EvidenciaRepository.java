package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
    List<Evidencia> findByActividadIdAndEliminadoEnIsNullOrderByOrdenAscIdAsc(Long actividadId);

    Optional<Evidencia> findByIdAndEliminadoEnIsNull(Long id);

    List<Evidencia> findByActividadIdAndEsPortadaTrueAndEliminadoEnIsNull(Long actividadId);
}
