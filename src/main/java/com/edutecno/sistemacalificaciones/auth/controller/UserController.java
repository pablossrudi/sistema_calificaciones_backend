package com.edutecno.sistemacalificaciones.auth.controller;

import com.edutecno.sistemacalificaciones.auth.dto.UserChangeStatusDTO;
import com.edutecno.sistemacalificaciones.auth.dto.UserResponseDTO;
import com.edutecno.sistemacalificaciones.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> changeStatus(@PathVariable Long userId, @RequestBody UserChangeStatusDTO changeStatusDTO) {
        UserResponseDTO user = userService.changeUserStatus(userId, changeStatusDTO);

        return ResponseEntity.ok(user);
    }
}
