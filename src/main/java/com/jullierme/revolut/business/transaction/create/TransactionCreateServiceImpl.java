package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.find.AccountFindByIdService;
import com.jullierme.revolut.business.transaction.find.TransactionFindByIdService;
import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;

import javax.ws.rs.NotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;

public class TransactionCreateServiceImpl implements TransactionCreateService {
    private static final String INSERT_TRANSACTION_SQL = "INSERT INTO TRANSACTION " +
            "(FROM_ACCOUNT_ID, TO_ACCOUNT_ID, AMOUNT, INSTANT) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_DEBIT_ACCOUNT_SQL = "UPDATE ACCOUNT SET " +
            "BALANCE = BALANCE - ? WHERE ID = ? AND ( BALANCE - ? ) >= 0";

    private static final String UPDATE_CREDIT_ACCOUNT_SQL = "UPDATE ACCOUNT SET " +
            "BALANCE = BALANCE + ? WHERE ID = ? AND ( BALANCE + ? ) >= 0";

    private DatabaseConnectionService databaseConnectionService;
    private TransactionFindByIdService transactionFindByIdService;
    private AccountFindByIdService accountFindByIdService;


    public TransactionCreateServiceImpl(DatabaseConnectionService databaseConnectionService,
                                        TransactionFindByIdService transactionFindByIdService,
                                        AccountFindByIdService accountFindByIdService) {
        this.databaseConnectionService = databaseConnectionService;
        this.transactionFindByIdService = transactionFindByIdService;
        this.accountFindByIdService = accountFindByIdService;
    }

    private Account getAccount(Long accountNumber) {
        if (accountNumber == null)
            throw new IllegalArgumentException();

        return accountFindByIdService.find(accountNumber)
                .orElseThrow(() -> new NotFoundException("Account " + accountNumber + " not found"));
    }

    @Override
    public Transaction create(TransactionRequest req) throws SQLException {
        if (req == null || req.getAmount() == null ||
                req.getAccountNumberFrom() == null || req.getAccountNumberTo() == null)
            throw new IllegalArgumentException();

        Account accountFrom = getAccount(req.getAccountNumberFrom());
        Account accountTo = getAccount(req.getAccountNumberTo());

        try (Connection conn = databaseConnectionService.getConnection()) {
            Long transactionId;

            transactionId = createTransaction(req, accountFrom, accountTo, conn);

            debitFromAccount(req, accountFrom, conn);

            creditToAccount(req, accountTo, conn);

            conn.commit();

            return transactionFindByIdService.find(transactionId)
                    .orElseThrow(() -> new SQLException("Internal server error"));
        }
    }

    private void debitFromAccount(final TransactionRequest req,
                                  final Account accountFrom,
                                  final Connection conn) throws SQLException {
        changeBalance(req, accountFrom, conn, UPDATE_DEBIT_ACCOUNT_SQL, "Insufficient funds");
    }

    private void creditToAccount(final TransactionRequest req,
                                 final Account accountTo,
                                 final Connection conn) throws SQLException {
        changeBalance(req, accountTo, conn, UPDATE_CREDIT_ACCOUNT_SQL, "Internal server error");
    }

    private void changeBalance(final TransactionRequest req,
                               final Account accountTo,
                               final Connection conn,
                               final String sql,
                               final String errorMessage) throws SQLException, IllegalStateException {
        try (PreparedStatement psTransaction = conn.prepareStatement(sql)) {
            psTransaction.setBigDecimal(1, req.getAmount());
            psTransaction.setLong(2, accountTo.getId());
            psTransaction.setBigDecimal(3, req.getAmount());

            int result = psTransaction.executeUpdate();

            if (result == 0) {
                throw new IllegalStateException(errorMessage);
            }
        }
    }

    private Long createTransaction(TransactionRequest req,
                                   Account accountFrom,
                                   Account accountTo,
                                   Connection conn) throws SQLException {
        Long transactionId;

        try (PreparedStatement psTransaction = conn.prepareStatement(INSERT_TRANSACTION_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            psTransaction.setLong(1, accountFrom.getId());
            psTransaction.setLong(2, accountTo.getId());
            psTransaction.setBigDecimal(3, req.getAmount());
            psTransaction.setTimestamp(4, Timestamp.from(Instant.now()));

            psTransaction.executeUpdate();

            ResultSet generatedKeys = psTransaction.getGeneratedKeys();

            generatedKeys.next();

            transactionId = generatedKeys.getLong(1);
        }

        return transactionId;
    }
}
