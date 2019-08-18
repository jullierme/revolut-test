package com.jullierme.revolut.model.account;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private Long id;
    private String name; /*In a real world, should has a person table*/
    private Integer accountNumber;
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

    public Account(Long id,
                   String name,
                   Integer accountNumber,
                   BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
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

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accountNumber, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
