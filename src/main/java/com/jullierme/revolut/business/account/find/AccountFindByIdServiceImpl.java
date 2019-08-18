package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountFindByIdServiceImpl implements AccountFindByIdService{
    private static final String FIND_ACCOUNT_BY_ID_SQL = "SELECT * FROM ACCOUNT WHERE ID = ?";

    private DatabaseConnectionService databaseConnectionService;

    public AccountFindByIdServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @Override
    public Optional<Account> find(Long id) {
        if (id == null)
            return Optional.empty();

        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ACCOUNT_BY_ID_SQL)) {

            ps.setLong(1, id);

            Account entity = loadEntity(ps);

            conn.commit();
            ps.close();

            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account loadEntity(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();

        Account entity = null;

        while (rs.next()) {
            entity = new Account(
                    rs.getLong("ID"),
                    rs.getString("NAME"),
                    rs.getBigDecimal("BALANCE"));

        }

        rs.close();

        return entity;
    }
}
