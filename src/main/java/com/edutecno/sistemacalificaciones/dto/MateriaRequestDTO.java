package com.edutecno.sistemacalificaciones.dto;

import com.edutecno.sistemacalificaciones.model.Alumno;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MateriaRequestDTO {
    private String nombre;
    private Long alumnoId;
}
