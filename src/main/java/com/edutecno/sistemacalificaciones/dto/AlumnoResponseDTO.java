package com.edutecno.sistemacalificaciones.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AlumnoResponseDTO {
    private Long alumnoId;
    private String rut;
    private String nombre;
    private String direccion;
    private Set<String> materias;
}
