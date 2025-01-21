package com.edutecno.sistemacalificaciones.mapper;

import com.edutecno.sistemacalificaciones.dto.AlumnoRequestDTO;
import com.edutecno.sistemacalificaciones.dto.AlumnoResponseDTO;
import com.edutecno.sistemacalificaciones.model.Alumno;
import com.edutecno.sistemacalificaciones.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AlumnoMapper {
    public AlumnoResponseDTO toDTO(Alumno alumno) {
        return AlumnoResponseDTO.builder()
                .alumnoId(alumno.getAlumnoId())
                .rut(alumno.getAlumnoRut())
                .nombre(alumno.getAlumnoNombre())
                .direccion(alumno.getAlumnoDireccion())
                .materias(alumno.getMaterias() != null ?
                        alumno.getMaterias().stream()
                                .map(Materia::getMateriaNombre)
                                .collect(Collectors.toSet()): null)
                .build();
    }

    public Alumno toEntity(AlumnoRequestDTO alumnoRequestDTO) {
        Alumno alumno = new Alumno();
        alumno.setAlumnoRut(alumnoRequestDTO.getRut());
        alumno.setAlumnoNombre(alumnoRequestDTO.getNombre());
        alumno.setAlumnoDireccion(alumnoRequestDTO.getDireccion());

        return alumno;
    }

    public Page<AlumnoResponseDTO> toDTO(Page<Alumno> alumnos) {
        return alumnos.map(this::toDTO);
    }

}
