package com.edutecno.sistemacalificaciones.auth.mapper;

import com.edutecno.sistemacalificaciones.auth.dto.UserResponseDTO;
import com.edutecno.sistemacalificaciones.auth.model.Role;
import com.edutecno.sistemacalificaciones.auth.model.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUserName(),
                user.getRut(),
                user.getEmail(),
                user.getActive(),
                user.getRoles().stream()
                        .map(Role::getMombre)
                        .collect(Collectors.toSet())
        );
    }
}
