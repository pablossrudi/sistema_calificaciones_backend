package com.edutecno.sistemacalificaciones.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumno_id", nullable = false)
    private Long alumnoId;
    @Column(name = "alumno_rut", nullable = false, length = 13)
    private String alumnoRut;
    @Column(name = "alumno_nombre", nullable = false, length = 50)
    private String alumnoNombre;
    @Column(name = "alumno_direccion", nullable = false, length = 50)
    private String alumnoDireccion;

    @OneToMany(mappedBy = "alumnoId", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Materia> materias = new HashSet<>();
}
