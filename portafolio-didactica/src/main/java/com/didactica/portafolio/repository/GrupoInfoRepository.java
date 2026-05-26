package com.didactica.portafolio.repository;

import com.didactica.portafolio.entity.GrupoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoInfoRepository extends JpaRepository<GrupoInfo, Long> {
    Optional<GrupoInfo> findFirstByActivoTrueOrderByIdAsc();

    Optional<GrupoInfo> findFirstByOrderByIdAsc();
}
