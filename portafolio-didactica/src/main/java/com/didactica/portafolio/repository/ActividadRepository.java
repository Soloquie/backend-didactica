package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Actividad;
import com.didactica.portafolio.enums.EstadoActividad;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    @EntityGraph(attributePaths = "categoria")
    List<Actividad> findByEliminadoEnIsNullOrderByOrdenAscCreadoEnDesc();

    @EntityGraph(attributePaths = "categoria")
    List<Actividad> findByEstadoAndEliminadoEnIsNullOrderByOrdenAscPublicadoEnDesc(EstadoActividad estado);

    @EntityGraph(attributePaths = "categoria")
    List<Actividad> findByEstadoAndDestacadoTrueAndEliminadoEnIsNullOrderByOrdenAscPublicadoEnDesc(EstadoActividad estado);

    @EntityGraph(attributePaths = "categoria")
    List<Actividad> findByCategoriaSlugAndEstadoAndEliminadoEnIsNullOrderByOrdenAscPublicadoEnDesc(String slugCategoria, EstadoActividad estado);

    @EntityGraph(attributePaths = "categoria")
    Optional<Actividad> findBySlugAndEstadoAndEliminadoEnIsNull(String slug, EstadoActividad estado);

    @EntityGraph(attributePaths = "categoria")
    Optional<Actividad> findByIdAndEliminadoEnIsNull(Long id);

    boolean existsBySlugAndEliminadoEnIsNull(String slug);
}
