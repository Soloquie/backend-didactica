package com.didactica.portafolio.entity;

import com.didactica.portafolio.enums.TipoEvidencia;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "evidencia")
public class Evidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoEvidencia tipo;

    @Column(name = "url_archivo")
    private String urlArchivo;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "peso_bytes")
    private Long pesoBytes;

    private Integer orden;

    @Column(name = "es_portada")
    private Boolean esPortada;

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
        if (esPortada == null) esPortada = false;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = Instant.now();
    }
}
