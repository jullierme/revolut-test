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

            conn.setAutoCommit(false);

            String sql = "INSERT INTO ACCOUNT (NAME, ACCOUNT_NUMBER, SORT_CODE, BALANCE) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAccountNumber());
            ps.setString(3, entity.getSortCode());
            ps.setBigDecimal(4, entity.getBalance());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                conn.commit();

                long id = generatedKeys.getLong(1);
                entity.setId(id);
            } else {
                throw new RuntimeException("Cound not save the account");
            }

            ps.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return entity;
    }
}
