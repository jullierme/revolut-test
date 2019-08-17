package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;

public class TransactionRequest {
    private String accountNumberFrom;
    private String sortCodeFrom;
    private String accountNumberTo;
    private String sortCodeTo;
    private BigDecimal amount;

    public TransactionRequest() {}

    public TransactionRequest(String accountNumberFrom, String sortCodeFrom,
                              String accountNumberTo, String sortCodeTo, BigDecimal amount) {
        this.accountNumberFrom = accountNumberFrom;
        this.sortCodeFrom = sortCodeFrom;
        this.accountNumberTo = accountNumberTo;
        this.sortCodeTo = sortCodeTo;
        this.amount = amount;
    }

    public String getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public String getSortCodeFrom() {
        return sortCodeFrom;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public String getSortCodeTo() {
        return sortCodeTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
