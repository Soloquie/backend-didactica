package com.didactica.portafolio.entity;

import com.didactica.portafolio.enums.EstadoActividad;
import com.didactica.portafolio.enums.TipoActividad;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actualizado_por")
    private Usuario actualizadoPor;

    private String nombre;

    @Column(unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    private TipoActividad tipo;

    @Enumerated(EnumType.STRING)
    private EstadoActividad estado;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "objetivo_didactico", columnDefinition = "TEXT")
    private String objetivoDidactico;

    @Column(columnDefinition = "TEXT")
    private String metodologia;

    @Column(name = "publico_objetivo")
    private String publicoObjetivo;

    private String lugar;

    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;

    @Column(name = "portada_url")
    private String portadaUrl;

    @Column(name = "reflexion_final", columnDefinition = "TEXT")
    private String reflexionFinal;

    private Boolean destacado;
    private Integer orden;

    @Column(name = "creado_en", updatable = false)
    private Instant creadoEn;

    @Column(name = "actualizado_en")
    private Instant actualizadoEn;

    @Column(name = "publicado_en")
    private Instant publicadoEn;

    @Column(name = "eliminado_en")
    private Instant eliminadoEn;

    @Builder.Default
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Evidencia> evidencias = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Aprendizaje> aprendizajes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Material> materiales = new ArrayList<>();

    @PrePersist
    void prePersist() {
        creadoEn = Instant.now();
        actualizadoEn = creadoEn;
        if (estado == null) estado = EstadoActividad.BORRADOR;
        if (destacado == null) destacado = false;
        if (orden == null) orden = 0;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = Instant.now();
    }
}
