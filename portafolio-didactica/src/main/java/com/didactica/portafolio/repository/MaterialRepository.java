package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByActividadIdAndEliminadoEnIsNullOrderByIdAsc(Long actividadId);

    Optional<Material> findByIdAndEliminadoEnIsNull(Long id);
}
