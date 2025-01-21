package com.edutecno.sistemacalificaciones.auth.dto;

public class AuthenticationResponse {
    private String role;
    private String jwt;

    public AuthenticationResponse(String role, String jwt) {
        this.role = role;
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
