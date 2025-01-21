package com.edutecno.sistemacalificaciones.auth.dto;

import java.util.Objects;
import java.util.Set;

public class UserResponseDTO {
    private Long userId;
    private String userName;
    private String rut;
    private String email;
    private Boolean isActive;
    private Set<String> roles;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long userId, String userName, String rut, String email, Boolean isActive, Set<String> roles) {
        this.userId = userId;
        this.userName = userName;
        this.rut = rut;
        this.email = email;
        this.isActive = isActive;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserResponseDTO other = (UserResponseDTO) obj;
        return Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName) && Objects.equals(rut, other.rut) && Objects.equals(email, other.email) && Objects.equals(isActive, other.isActive) && roles.equals(other.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, rut, email, isActive, roles);
    }
}
