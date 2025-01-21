package com.edutecno.sistemacalificaciones.service;

import com.edutecno.sistemacalificaciones.Repository.AlumnoRepository;
import com.edutecno.sistemacalificaciones.Repository.MateriaRepository;
import com.edutecno.sistemacalificaciones.dto.MateriaRequestDTO;
import com.edutecno.sistemacalificaciones.dto.MateriaResponseDTO;
import com.edutecno.sistemacalificaciones.mapper.MateriaMapper;
import com.edutecno.sistemacalificaciones.model.Alumno;
import com.edutecno.sistemacalificaciones.model.Materia;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MateriaServiceImp implements MateriaService {
    private MateriaRepository materiaRepository;
    private MateriaMapper materiaMapper;
    private AlumnoRepository alumnoRepository;

    @Override
    public MateriaResponseDTO getMateriaById(Long id) {
        return materiaMapper.toDTO(materiaRepository.findMateriaById(id));
    }

    @Override
    public Page<MateriaResponseDTO> getAllMaterias(Pageable pageable) {
        return materiaMapper.toDTO(materiaRepository.findAllMaterias(pageable));
    }

    @Override
    public void saveMateria(MateriaRequestDTO materiaRequestDTO) {
        Materia materia = materiaMapper.toEntity(materiaRequestDTO);
        Alumno alumno = alumnoRepository.findAlumnosById(materiaRequestDTO.getAlumnoId());
        if (alumno != null) {
            materia.setAlumnoId(alumno);
        }
        materiaRepository.save(materia);
    }

}
