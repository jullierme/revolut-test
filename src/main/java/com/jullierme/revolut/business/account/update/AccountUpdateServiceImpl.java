package com.jullierme.revolut.business.account.update;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountUpdateServiceImpl implements AccountUpdateService {
    private static final String UPDATE_ACCOUNT_SQL = "UPDATE ACCOUNT SET " +
            "NAME = ?, ACCOUNT_NUMBER = ?, SORT_CODE = ?, BALANCE = ? WHERE ID = ?";

    private DatabaseConnectionService databaseConnectionService;

    public AccountUpdateServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    private void validateEntitySaved(Long id, Account entity) {
        if (id == null || entity == null || entity.getId() == null)
            throw new IllegalArgumentException("ID and ENTITY are required!");

        if (!id.equals(entity.getId()))
            throw new IllegalArgumentException();
    }

    @Override
    public Account update(Long id, Account entity) throws SQLException {
        validateEntitySaved(id, entity);

        return update(entity);
    }

    @Override
    public Account update(Account entity) throws SQLException {
        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ACCOUNT_SQL)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAccountNumber());
            ps.setString(3, entity.getSortCode());
            ps.setBigDecimal(4, entity.getBalance());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();

            conn.commit();

            return entity;
        }
    }
}
