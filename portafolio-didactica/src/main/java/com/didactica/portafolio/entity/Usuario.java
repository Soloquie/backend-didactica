package com.didactica.portafolio.entity;

import com.didactica.portafolio.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String correo;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

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
