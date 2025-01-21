package com.edutecno.sistemacalificaciones.service;

import com.edutecno.sistemacalificaciones.dto.AlumnoRequestDTO;
import com.edutecno.sistemacalificaciones.dto.AlumnoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlumnoService {
    AlumnoResponseDTO findAlumnoById(Long id);
    Page<AlumnoResponseDTO> findAllAlumnos(Pageable pageable);
    void saveAlumno(AlumnoRequestDTO alumnoRequestDTO);
}
