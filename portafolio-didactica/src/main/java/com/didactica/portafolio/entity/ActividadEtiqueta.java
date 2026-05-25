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
@Table(name = "actividad_etiqueta")
public class ActividadEtiqueta {
    @EmbeddedId
    private ActividadEtiquetaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actividadId")
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("etiquetaId")
    @JoinColumn(name = "etiqueta_id")
    private Etiqueta etiqueta;

    @Column(name = "creado_en", updatable = false)
    private Instant creadoEn;

    @PrePersist
    void prePersist() {
        creadoEn = Instant.now();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class ActividadEtiquetaId implements java.io.Serializable {
        @Column(name = "actividad_id")
        private Long actividadId;

        @Column(name = "etiqueta_id")
        private Long etiquetaId;
    }
}
