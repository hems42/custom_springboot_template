package com.org.custom_springboot_template.product.request.createRequest;

import com.org.custom_springboot_template.entity.security.Role;
import com.org.custom_springboot_template.product.enums.ProductEnumTransactionTypes;

public class LogRoleRequestCreate {

    private ProductEnumTransactionTypes transactionType;

    private Role role;

    public LogRoleRequestCreate() {
    }

    public LogRoleRequestCreate(ProductEnumTransactionTypes transactionType, Role role) {
        this.transactionType = transactionType;
        this.role = role;
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
}
