package com.jullierme.revolut.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;

public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

    static {
        try {
            Class.forName(configuration.getString("datasource.driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load h2 driver", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        final String url = configuration.getString("datasource.url");
        final String username = configuration.getString("datasource.username");

        final Connection conn = DriverManager.getConnection(url, username, "");
        conn.setAutoCommit(false);

        return conn;
    }
}
