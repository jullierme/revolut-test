package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;

public class TransactionRequestBuilder {
    private Integer accountNumberFrom;
    private Integer accountNumberTo;
    private BigDecimal amount;

    public static TransactionRequestBuilder builder() {
        return new TransactionRequestBuilder();
    }

    public TransactionRequestBuilder accountNumberFrom(Integer accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
        return this;
    }

    public TransactionRequestBuilder accountNumberTo(Integer accountNumberTo) {
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
