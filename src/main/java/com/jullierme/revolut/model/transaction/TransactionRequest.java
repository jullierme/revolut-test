package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;

public class TransactionRequest {
    private Long accountNumberFrom;
    private Long accountNumberTo;
    private BigDecimal amount;

    public TransactionRequest() {}

    public TransactionRequest(Long accountNumberFrom,
                              Long accountNumberTo,
                              BigDecimal amount) {
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.amount = amount;
    }

    public Long getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public Long getAccountNumberTo() {
        return accountNumberTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
