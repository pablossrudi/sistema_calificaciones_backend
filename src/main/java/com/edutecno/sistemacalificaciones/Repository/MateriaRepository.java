package com.edutecno.sistemacalificaciones.Repository;

import com.edutecno.sistemacalificaciones.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    @Query("SELECT mt FROM Materia mt WHERE mt.materiaId = :id")
    Materia findMateriaById(@Param("id") Long id);

    @Query("SELECT mt FROM Materia mt ORDER BY mt.materiaId ASC")
    Page<Materia> findAllMaterias(Pageable pageable);
}
