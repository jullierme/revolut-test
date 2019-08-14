package com.jullierme.revolut.business.account;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
public class AccountCreateServiceImpl implements AccountCreateService {

    private DatabaseConnectionService databaseConnectionService;

    @Inject
    public AccountCreateServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @Override
    public Account create(Account entity) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = databaseConnectionService.getConnection();
            stmt = conn.createStatement();

            final String sql = "INSERT INTO ACCOUNT (NAME, ACCOUNT_NUMBER, SORT_CODE, BALANCE) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAccountNumber());
            ps.setString(3, entity.getSortCode());
            ps.setBigDecimal(4, entity.getBalance());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
                return entity;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ignored) {
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        throw new RuntimeException("Could not create the account");
    }
}
