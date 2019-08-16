package com.jullierme.revolut.model.transaction;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionBuilder {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private Instant instant;

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public TransactionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder fromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
        return this;
    }

    public TransactionBuilder toAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public TransactionBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder instant(Instant instant) {
        this.instant = instant;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, fromAccountId, toAccountId, amount, instant);
    }
}
