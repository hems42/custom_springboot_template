package com.org.custom_springboot_template.product.request.createRequest;

import product.enums.ProductEnumTransactionTypes;

public class LogUserRequestCreate {

    private ProductEnumTransactionTypes transactionType;

    public LogUserRequestCreate() {
    }

    public LogUserRequestCreate(ProductEnumTransactionTypes transactionType) {
        this.transactionType = transactionType;
    }

    public ProductEnumTransactionTypes getUserOperationType() {
        return transactionType;
    }

    public void setUserOperationType(ProductEnumTransactionTypes transactionType) {
        this.transactionType = transactionType;
    }
}
