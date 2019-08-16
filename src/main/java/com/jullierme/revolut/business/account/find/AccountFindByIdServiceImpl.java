package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountFindByIdServiceImpl implements AccountFindByIdService,
        AccountFindByAccountService {
    private static final String FIND_ACCOUNT_BY_ID_SQL = "SELECT * FROM ACCOUNT WHERE ID = ?";
    private static final String FIND_ACCOUNT_BY_ACCOUNT = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ? and SORT_CODE = ?";

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

    @Override
    public Optional<Account> find(String accountNumber, String sortCode) {
        if (accountNumber == null || sortCode == null)
            return Optional.empty();

        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ACCOUNT_BY_ACCOUNT)) {

            ps.setString(1, accountNumber);
            ps.setString(2, sortCode);

            Account entity = loadEntity(ps);

            conn.commit();
            ps.close();

            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account loadEntity(PreparedStatement ps) throws SQLException {
        if (ps == null)
            return null;

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

        return entity;
    }
}