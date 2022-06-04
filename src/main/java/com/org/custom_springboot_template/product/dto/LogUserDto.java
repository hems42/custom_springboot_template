package com.org.custom_springboot_template.product.dto;

import com.org.custom_springboot_template.product.enums.ProductEnumTransactionTypes;
import entity.primary.User;

import java.time.LocalDateTime;

public class LogUserDto {

    private Integer id;

    private ProductEnumTransactionTypes transactionType;

    private User user;

    private LocalDateTime createdDate;

    public LogUserDto() {
    }

    public LogUserDto(
            Integer id,
            ProductEnumTransactionTypes transactionType,
            User user,
            LocalDateTime createdDate) {
        this.id = id;
        this.transactionType = transactionType;

        this.user = user;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEnumTransactionTypes getUserOperationType() {
        return transactionType;
    }

    public void setUserOperationType(ProductEnumTransactionTypes transactionType) {
        this.transactionType = transactionType;
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
