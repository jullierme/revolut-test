package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;

public class TransactionRequest {
    private Integer accountNumberFrom;
    private Integer accountNumberTo;
    private BigDecimal amount;

    public TransactionRequest() {}

    public TransactionRequest(Integer accountNumberFrom,
                              Integer accountNumberTo,
                              BigDecimal amount) {
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.amount = amount;
    }

    public Integer getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public Integer getAccountNumberTo() {
        return accountNumberTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
