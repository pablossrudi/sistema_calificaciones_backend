package com.edutecno.sistemacalificaciones.auth.service;

import com.edutecno.sistemacalificaciones.auth.dto.UserChangeStatusDTO;
import com.edutecno.sistemacalificaciones.auth.dto.UserCreateDTO;
import com.edutecno.sistemacalificaciones.auth.dto.UserResponseDTO;
import com.edutecno.sistemacalificaciones.auth.mapper.UserMapper;
import com.edutecno.sistemacalificaciones.auth.model.Role;
import com.edutecno.sistemacalificaciones.auth.model.User;
import com.edutecno.sistemacalificaciones.auth.repository.RoleRepository;
import com.edutecno.sistemacalificaciones.auth.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUserName(userCreateDTO.getUserName());
        user.setRut(userCreateDTO.getRut());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setEmail(userCreateDTO.getEmail());
        user.setActive(true);
        user.setRoles(mapRoles(userCreateDTO.getRoles()));

        User userSaved = userRepository.save(user);
        return UserMapper.toDTO(userSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO changeUserStatus(Long id, UserChangeStatusDTO userChangeStatusDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userChangeStatusDTO.getActive() != null){
            user.setActive(userChangeStatusDTO.getActive());

            User updatedUser = userRepository.save(user);

            return UserMapper.toDTO(updatedUser);
        } else {
            return UserMapper.toDTO(user);
        }

    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Set<Role> mapRoles(Set<String> roles) {
        return roles.stream()
                .map(nombre -> roleRepository.findByNombre(nombre.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + nombre)))
                .collect(Collectors.toSet());
    }
}
