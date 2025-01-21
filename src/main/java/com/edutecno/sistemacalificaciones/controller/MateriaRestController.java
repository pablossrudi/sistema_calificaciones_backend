package com.edutecno.sistemacalificaciones.controller;

import com.edutecno.sistemacalificaciones.dto.MateriaRequestDTO;
import com.edutecno.sistemacalificaciones.dto.MateriaResponseDTO;
import com.edutecno.sistemacalificaciones.service.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaRestController {
    private final MateriaService materiaService;

    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponseDTO> getMateriaById(@PathVariable Long id) {
        MateriaResponseDTO materia = materiaService.getMateriaById(id);
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materia);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMaterias(
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<MateriaResponseDTO> materiaPage = materiaService.getAllMaterias(pageRequest);

        return ResponseEntity.ok(createPaginationResponse(materiaPage));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MateriaResponseDTO> saveMateria(@RequestBody MateriaRequestDTO materiaRequestDTO) {
        materiaService.saveMateria(materiaRequestDTO);
        return ResponseEntity.ok().build();
    }

    private Map<String, Object> createPaginationResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("numberOfElements", page.getNumberOfElements());
        response.put("numberPage", page.getNumber());
        response.put("materias", page.getContent());
        return response;
    }
}
