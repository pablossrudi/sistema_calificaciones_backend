package com.edutecno.sistemacalificaciones.auth.service;

import com.edutecno.sistemacalificaciones.auth.dto.UserChangeStatusDTO;
import com.edutecno.sistemacalificaciones.auth.dto.UserCreateDTO;
import com.edutecno.sistemacalificaciones.auth.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO changeUserStatus(Long id, UserChangeStatusDTO userChangeStatusDTO);
    List<UserResponseDTO> getAllUsers();
}
