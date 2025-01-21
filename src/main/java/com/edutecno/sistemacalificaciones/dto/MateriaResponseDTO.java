package com.edutecno.sistemacalificaciones.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MateriaResponseDTO {
    private Long materiaId;
    private String materiaNombre;
    private AlumnoResponseDTO alumno;

}
