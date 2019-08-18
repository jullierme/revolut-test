package com.jullierme.revolut.model.account;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private String name; /*In a real world, should has a person table*/
    private BigDecimal balance;

    public Account() {
    }

    public Account(Long id,
                   String name,
                   BigDecimal balance) {
        this.id = id;
        this.name = name;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
