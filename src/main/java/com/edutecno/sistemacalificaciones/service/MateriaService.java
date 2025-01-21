package com.edutecno.sistemacalificaciones.service;

import com.edutecno.sistemacalificaciones.dto.MateriaRequestDTO;
import com.edutecno.sistemacalificaciones.dto.MateriaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MateriaService {
    MateriaResponseDTO getMateriaById(Long id);
    Page<MateriaResponseDTO> getAllMaterias(Pageable pageable);
    void saveMateria(MateriaRequestDTO materiaRequestDTO);
}
