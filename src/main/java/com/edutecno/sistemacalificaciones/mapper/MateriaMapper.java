package com.edutecno.sistemacalificaciones.mapper;

import com.edutecno.sistemacalificaciones.dto.AlumnoResponseDTO;
import com.edutecno.sistemacalificaciones.dto.MateriaRequestDTO;
import com.edutecno.sistemacalificaciones.dto.MateriaResponseDTO;
import com.edutecno.sistemacalificaciones.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MateriaMapper {
    public MateriaResponseDTO toDTO(Materia materia) {
        return MateriaResponseDTO.builder()
                .materiaId(materia.getMateriaId())
                .materiaNombre(materia.getMateriaNombre())
                .alumno(AlumnoResponseDTO.builder()
                        .alumnoId(materia.getAlumnoId().getAlumnoId())
                        .rut(materia.getAlumnoId().getAlumnoRut())
                        .nombre(materia.getAlumnoId().getAlumnoNombre())
                        .direccion(materia.getAlumnoId().getAlumnoDireccion())
                        .materias(materia.getAlumnoId().getMaterias() != null ?
                            materia.getAlumnoId().getMaterias().stream()
                                    .map(Materia::getMateriaNombre)
                                    .collect(Collectors.toSet()): null)
                        .build())
                .build();
    }

    public Materia toEntity(MateriaRequestDTO materiaRequestDTO) {
        Materia materia = new Materia();
        materia.setMateriaNombre(materiaRequestDTO.getNombre());

        return materia;
    }

    public Page<MateriaResponseDTO> toDTO(Page<Materia> materias) {
        return materias.map(this::toDTO);
    }

}
