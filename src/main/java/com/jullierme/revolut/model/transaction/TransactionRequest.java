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

    public void setAccountNumberFrom(String accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public String getSortCodeFrom() {
        return sortCodeFrom;
    }

    public void setSortCodeFrom(String sortCodeFrom) {
        this.sortCodeFrom = sortCodeFrom;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public String getSortCodeTo() {
        return sortCodeTo;
    }

    public void setSortCodeTo(String sortCodeTo) {
        this.sortCodeTo = sortCodeTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
