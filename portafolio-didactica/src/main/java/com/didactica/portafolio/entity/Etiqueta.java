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
@Table(name = "etiqueta")
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String slug;

    @Column(name = "creado_en", updatable = false)
    private Instant creadoEn;

    @PrePersist
    void prePersist() {
        creadoEn = Instant.now();
    }
}
