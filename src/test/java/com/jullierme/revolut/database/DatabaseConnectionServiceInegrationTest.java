package com.jullierme.revolut.database;

import com.jullierme.revolut.config.integration.extension.commonsConfigurations.BasicConfigurationIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

@BasicConfigurationIntegrationTest
public class DatabaseConnectionServiceInegrationTest {
    private DatabaseConnectionService service;
    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    private Connection connection;

    @BeforeEach
    void beforeEach() throws SQLException {
        instantiateServices();
        closeConnection();
    }

    private void instantiateServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        service = databaseConnectionServiceFactory.getInstance();
    }

    private void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void shouldReturnAValidConnection() throws SQLException {
        connection = service.getConnection();

        Assertions.assertNotNull(connection);
        Assertions.assertFalse(connection.isClosed());
    }
}
