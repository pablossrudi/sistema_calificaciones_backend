package com.edutecno.sistemacalificaciones.service;

import com.edutecno.sistemacalificaciones.Repository.AlumnoRepository;
import com.edutecno.sistemacalificaciones.dto.AlumnoRequestDTO;
import com.edutecno.sistemacalificaciones.dto.AlumnoResponseDTO;
import com.edutecno.sistemacalificaciones.mapper.AlumnoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImp implements AlumnoService{
    private final AlumnoRepository alumnosRepository;
    private final AlumnoMapper alumnoMapper;

    @Override
    public AlumnoResponseDTO findAlumnoById(Long id) {
        return alumnoMapper.toDTO(alumnosRepository.findAlumnosById(id));
    }

    @Override
    public Page<AlumnoResponseDTO> findAllAlumnos(Pageable pageable) {
        return alumnoMapper.toDTO(alumnosRepository.findAllAlumnos(pageable));
    }

    @Override
    public void saveAlumno(AlumnoRequestDTO alumnoRequestDTO) {
        alumnosRepository.save(alumnoMapper.toEntity(alumnoRequestDTO));
    }
}
