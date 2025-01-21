package com.edutecno.sistemacalificaciones.Repository;

import com.edutecno.sistemacalificaciones.model.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    @Query("SELECT al FROM Alumno al WHERE al.alumnoId = :id")
    Alumno findAlumnosById(@Param("id") Long id);

    @Query("SELECT al FROM Alumno al ORDER BY al.alumnoId ASC")
    Page<Alumno> findAllAlumnos(Pageable pageable);

}
