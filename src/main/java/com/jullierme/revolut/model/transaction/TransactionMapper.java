package com.jullierme.revolut.model.transaction;

public interface TransactionMapper {
    TransactionDto toTransactionDto(Transaction account);
}
