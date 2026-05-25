package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.Portafolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortafolioRepository extends JpaRepository<Portafolio, Long> {
    Optional<Portafolio> findFirstByActivoTrueAndEliminadoEnIsNullOrderByIdAsc();

    List<Portafolio> findByEliminadoEnIsNullOrderByIdAsc();
}
