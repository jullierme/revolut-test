package com.jullierme.revolut.business.account;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.exceptions.BusinessException;
import com.jullierme.revolut.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountCreateServiceImpl implements AccountCreateService {
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNT " +
            "(NAME, ACCOUNT_NUMBER, SORT_CODE, BALANCE) VALUES (?, ?, ?, ?)";

    private DatabaseConnectionService databaseConnectionService;

    public AccountCreateServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @Override
    public Account create(Account entity) {
        try (Connection conn = databaseConnectionService.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement(INSERT_ACCOUNT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getAccountNumber());
                ps.setString(3, entity.getSortCode());
                ps.setBigDecimal(4, entity.getBalance());

                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    conn.commit();

                    entity.setId(generatedKeys.getLong(1));
                } else {
                    throw new BusinessException("Error to retrieve the id");
                }

            } catch (SQLException e) {
                e.printStackTrace();

                rollback(conn);

                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new RuntimeException("Error Establishing a Database Connection");
        }

        return entity;
    }

    private void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
