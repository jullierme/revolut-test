package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;

public class TransactionRequestBuilder {
    private String accountNumberFrom;
    private String sortCodeFrom;
    private String accountNumberTo;
    private String sortCodeTo;
    private BigDecimal amount;

    public static TransactionRequestBuilder builder() {
        return new TransactionRequestBuilder();
    }

    public TransactionRequestBuilder accountNumberFrom(String accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
        return this;
    }

    public TransactionRequestBuilder sortCodeFrom(String sortCodeFrom) {
        this.sortCodeFrom = sortCodeFrom;
        return this;
    }

    public TransactionRequestBuilder accountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
        return this;
    }

    public TransactionRequestBuilder sortCodeTo(String sortCodeTo) {
        this.sortCodeTo = sortCodeTo;
        return this;
    }
    public TransactionRequestBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionRequest build() {
        return new TransactionRequest(accountNumberFrom, sortCodeFrom, accountNumberTo, sortCodeTo, amount);
    }
}
