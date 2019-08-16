package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;

import java.sql.SQLException;

public interface TransactionCreateService {
    Transaction create(TransactionRequest request) throws SQLException;
}
