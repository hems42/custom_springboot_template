package com.org.custom_springboot_template.product.response;

import entity.security.Role;

import java.time.LocalDateTime;
import java.util.Set;

public class LoginResponse {

    private String id;

    private String username;

    private String email;

    private Boolean isActive;

    private Boolean isRegistered;

    private Set<Role> roles;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String accessToken;

    private String refreshToken;

    public LoginResponse() {
    }

    public LoginResponse(String id,
                         String username,
                         String email,
                         Boolean isActive,
                         Boolean isRegistered,
                         Set<Role> roles,
                         LocalDateTime createdDate,
                         LocalDateTime updatedDate,
                         String accessToken,
                         String refreshToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.isRegistered = isRegistered;
        this.roles = roles;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
