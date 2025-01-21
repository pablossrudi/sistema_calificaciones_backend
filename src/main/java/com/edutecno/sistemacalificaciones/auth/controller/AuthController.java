package com.edutecno.sistemacalificaciones.auth.controller;

import com.edutecno.sistemacalificaciones.auth.dto.AuthenticationRequest;
import com.edutecno.sistemacalificaciones.auth.dto.AuthenticationResponse;
import com.edutecno.sistemacalificaciones.auth.dto.UserCreateDTO;
import com.edutecno.sistemacalificaciones.auth.dto.UserResponseDTO;
import com.edutecno.sistemacalificaciones.auth.security.JwtUtil;
import com.edutecno.sistemacalificaciones.auth.service.AuthService;
import com.edutecno.sistemacalificaciones.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        if (!authService.userExists(authenticationRequest.getUsername())) {
            throw new UsernameNotFoundException("User not found: " + authenticationRequest.getUsername());
        }

        if (!authService.isUserActive(authenticationRequest.getUsername())) {
            throw new RuntimeException("User is not active: " + authenticationRequest.getUsername());
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        String role = authService.getUserRole(authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(role, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCreateDTO user) {
        UserResponseDTO userCreated = userService.createUser(user);

        return ResponseEntity.ok(userCreated);
    }
}
