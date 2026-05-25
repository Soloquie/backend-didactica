package com.didactica.portafolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aprendizaje")
public class Aprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer orden;

    @Column(name = "creado_en", updatable = false)
    private Instant creadoEn;

    @Column(name = "actualizado_en")
    private Instant actualizadoEn;

    @Column(name = "eliminado_en")
    private Instant eliminadoEn;

    @PrePersist
    void prePersist() {
        creadoEn = Instant.now();
        actualizadoEn = creadoEn;
        if (orden == null) orden = 0;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = Instant.now();
    }
}
