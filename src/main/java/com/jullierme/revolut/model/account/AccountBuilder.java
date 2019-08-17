package com.jullierme.revolut.model.account;

import java.math.BigDecimal;

public class AccountBuilder {
    private Long id;
    private String name;
    private Integer accountNumber;
    private BigDecimal balance;

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public AccountBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AccountBuilder accountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(id, name, accountNumber, balance);
    }
}
