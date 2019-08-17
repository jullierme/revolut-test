package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.find.AccountFindByIdService;
import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class AccountCreateServiceImpl implements AccountCreateService {
    private static final int MAX_ACCOUNT_NUMBER = 99999999;
    private static final int MIN_ACCOUNT_NUMBER = 10000000;

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNT " +
            "(NAME, ACCOUNT_NUMBER, BALANCE) VALUES (?, ?, ?)";

    private DatabaseConnectionService databaseConnectionService;
    private AccountFindByIdService accountFindByIdService;


    public AccountCreateServiceImpl(DatabaseConnectionService databaseConnectionService,
                                    AccountFindByIdService accountFindByIdService) {
        this.databaseConnectionService = databaseConnectionService;
        this.accountFindByIdService = accountFindByIdService;
    }

    @Override
    public Account create(Account entity) throws SQLException {
        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ACCOUNT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getName());
            ps.setInt(2, generateAccountNumber());
            ps.setBigDecimal(3, entity.getBalance());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            generatedKeys.next();

            conn.commit();

            return accountFindByIdService.find(generatedKeys.getLong(1))
                    .orElseThrow(() -> new SQLException("Internal server error"));
        }
    }

    private int generateAccountNumber() {
        return new Random().nextInt((MAX_ACCOUNT_NUMBER - MIN_ACCOUNT_NUMBER) + 1) + MIN_ACCOUNT_NUMBER;
    }
}
