package com.jullierme.revolut.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;

public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

    @Override
    public Connection getConnection() throws SQLException {
        final String url = configuration.getString("datasource.url");
        final String username = configuration.getString("datasource.username");

        return DriverManager.getConnection(url, username, "");
    }
}