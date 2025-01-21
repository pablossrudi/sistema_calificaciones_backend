package com.edutecno.sistemacalificaciones.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materia_id", nullable = false)
    private Long materiaId;
    @Column(name = "materia_nombre", nullable = false, length = 50)
    private String materiaNombre;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    @ToString.Exclude
    private Alumno alumnoId;

}
