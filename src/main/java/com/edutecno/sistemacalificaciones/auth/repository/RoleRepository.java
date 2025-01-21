package com.edutecno.sistemacalificaciones.auth.repository;

import com.edutecno.sistemacalificaciones.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNombre(String name);
}
