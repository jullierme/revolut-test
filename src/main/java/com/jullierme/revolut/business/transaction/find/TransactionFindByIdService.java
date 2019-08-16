package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.model.transaction.Transaction;

import java.util.Optional;

public interface TransactionFindByIdService {
    Optional<Transaction> find(Long id);
}
