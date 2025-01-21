package com.edutecno.sistemacalificaciones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlumnoRequestDTO {
    private String rut;
    private String nombre;
    private String direccion;
}
