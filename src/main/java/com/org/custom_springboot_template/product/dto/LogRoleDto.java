package com.org.custom_springboot_template.product.dto;

import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.Role;
import com.org.custom_springboot_template.product.enums.ProductEnumTransactionTypes;

import java.time.LocalDateTime;

public class LogRoleDto {

    private Integer id;

    private ProductEnumTransactionTypes transactionType;

    private Role role;

    private User user;

    private LocalDateTime createdDate;

    public LogRoleDto() {
    }

    public LogRoleDto(Integer id,
                      ProductEnumTransactionTypes transactionType,
                      Role role,
                      User user,
                      LocalDateTime createdDate) {
        this.id = id;
        this.transactionType = transactionType;
        this.role = role;
        this.user = user;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEnumTransactionTypes getCrud() {
        return transactionType;
    }

    public void setCrud(ProductEnumTransactionTypes transactionType) {
        this.transactionType = transactionType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
