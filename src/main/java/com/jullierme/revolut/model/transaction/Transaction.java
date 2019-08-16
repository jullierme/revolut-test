package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Transaction {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private Instant instant;

    public Transaction() {
    }

    public Transaction(Long id,
                       Long fromAccountId,
                       Long toAccountId,
                       BigDecimal amount,
                       Instant instant) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.instant = instant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fromAccountId, that.fromAccountId) &&
                Objects.equals(toAccountId, that.toAccountId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(instant, that.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromAccountId, toAccountId, amount, instant);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", amount=" + amount +
                ", instant=" + instant +
                '}';
    }
}
