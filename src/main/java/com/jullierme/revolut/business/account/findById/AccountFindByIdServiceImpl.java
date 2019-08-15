package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountFindByIdServiceImpl implements AccountFindByIdService {
    private static final String FIND_ACCOUNT_BY_ID_SQL = "SELECT * FROM ACCOUNT WHERE ID = ?";

    private DatabaseConnectionService databaseConnectionService;

    public AccountFindByIdServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @Override
    public Optional<Account> find(Long id) {
        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ACCOUNT_BY_ID_SQL)) {
            conn.setAutoCommit(true);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            Account entity = null;

            while (rs.next()) {
                entity = new Account(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("ACCOUNT_NUMBER"),
                        rs.getString("SORT_CODE"),
                        rs.getBigDecimal("BALANCE"));

            }

            rs.close();

            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
