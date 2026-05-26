package com.didactica.portafolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "grupo_info")
public class GrupoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    private Boolean activo;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "grupo_integrante", joinColumns = @JoinColumn(name = "grupo_info_id"))
    @Column(name = "nombre")
    @OrderColumn(name = "orden")
    private List<String> integrantes = new ArrayList<>();

    @Column(name = "creado_en", updatable = false)
    private Instant creadoEn;

    @Column(name = "actualizado_en")
    private Instant actualizadoEn;

    @PrePersist
    void prePersist() {
        creadoEn = Instant.now();
        actualizadoEn = creadoEn;
        if (activo == null) activo = true;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = Instant.now();
    }
}
