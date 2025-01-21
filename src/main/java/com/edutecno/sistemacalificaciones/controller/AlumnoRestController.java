package com.edutecno.sistemacalificaciones.controller;

import com.edutecno.sistemacalificaciones.dto.AlumnoRequestDTO;
import com.edutecno.sistemacalificaciones.dto.AlumnoResponseDTO;
import com.edutecno.sistemacalificaciones.service.AlumnoService;
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
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoRestController {
    private final AlumnoService alumnoService;

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> getAlumnoById(@PathVariable Long id) {
        AlumnoResponseDTO alumno = alumnoService.findAlumnoById(id);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAlumnos(
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<AlumnoResponseDTO> alumnoPage = alumnoService.findAllAlumnos(pageRequest);

        return ResponseEntity.ok(createPaginationResponse(alumnoPage));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AlumnoResponseDTO> saveAlumno(@RequestBody AlumnoRequestDTO alumno) {
        alumnoService.saveAlumno(alumno);
        return ResponseEntity.ok().build();
    }

    private Map<String, Object> createPaginationResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("numberOfElements", page.getNumberOfElements());
        response.put("numberPage", page.getNumber());
        response.put("alumnos", page.getContent());
        return response;
    }
}
