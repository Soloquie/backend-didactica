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
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String icono;
    private String color;
    private Integer orden;
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
        if (orden == null) orden = 0;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = Instant.now();
    }
}
