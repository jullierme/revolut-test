package com.jullierme.revolut.model.account;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private String name; /*In a real world, should has a person table*/
    private String accountNumber;
    private String sortCode;
    private BigDecimal balance;

    public Account() {
    }

    public Account(Long id,
                   String name,
                   String accountNumber,
                   String sortCode,
                   BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
