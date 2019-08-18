package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;
import java.time.Instant;

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

    public Long getToAccountId() {
        return toAccountId;
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

}
