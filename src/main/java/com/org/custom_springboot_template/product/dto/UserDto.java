package com.org.custom_springboot_template.product.dto;

import entity.security.Role;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {

    private String id;

    private String username;

    private String email;

    private String password;

    private Boolean isActive;

    private Boolean isRegistered;

    private Set<Role> roles;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public UserDto() {
    }

    public UserDto(
            String id,
            String username,
            String email,
            String password,
            Boolean isActive,
            Boolean isRegistered,
            Set<Role> roles, LocalDateTime createdDate,
            LocalDateTime updatedDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.isRegistered = isRegistered;
        this.roles = roles;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", nickName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", isRegistered=" + isRegistered +
                ", roles=" + roles +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
