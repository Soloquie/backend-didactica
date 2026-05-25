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
@Table(name = "portafolio")
public class Portafolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String subtitulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String materia;
    private String docente;
    private String institucion;

    @Column(name = "periodo_academico")
    private String periodoAcademico;

    @Column(name = "imagen_portada_url")
    private String imagenPortadaUrl;

    @Column(name = "color_principal")
    private String colorPrincipal;

    @Column(name = "color_secundario")
    private String colorSecundario;

    private Boolean activo;

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
        if (activo == null) activo = true;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = Instant.now();
    }
}
