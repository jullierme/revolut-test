package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.transaction.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionFindByIdServiceImpl implements TransactionFindByIdService {
    private static final String FIND_TRANSACTION_BY_ID_SQL = "SELECT * FROM TRANSACTION WHERE ID = ?";

    private DatabaseConnectionService databaseConnectionService;

    public TransactionFindByIdServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @Override
    public Optional<Transaction> find(Long id) {
        if (id == null)
            return Optional.empty();

        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_TRANSACTION_BY_ID_SQL)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            Transaction entity = null;

            while (rs.next()) {
                entity = new Transaction(
                        rs.getLong("ID"),
                        rs.getLong("FROM_ACCOUNT_ID"),
                        rs.getLong("TO_ACCOUNT_ID"),
                        rs.getBigDecimal("AMOUNT"),
                        rs.getTimestamp("INSTANT").toInstant()
                );

            }

            conn.commit();
            rs.close();

            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
