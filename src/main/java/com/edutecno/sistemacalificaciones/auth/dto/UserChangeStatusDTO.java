package com.edutecno.sistemacalificaciones.auth.dto;

public class UserChangeStatusDTO {

    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserCbangeStatusDTO{" +
                "isActive=" + isActive +
                '}';
    }
}
