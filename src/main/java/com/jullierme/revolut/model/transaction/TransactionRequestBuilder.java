package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;

public class TransactionRequestBuilder {
    private Long accountNumberFrom;
    private Long accountNumberTo;
    private BigDecimal amount;

    public static TransactionRequestBuilder builder() {
        return new TransactionRequestBuilder();
    }

    public TransactionRequestBuilder accountNumberFrom(Long accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
        return this;
    }

    public TransactionRequestBuilder accountNumberTo(Long accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
        return this;
    }

    public TransactionRequestBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionRequest build() {
        return new TransactionRequest(accountNumberFrom, accountNumberTo, amount);
    }
}
