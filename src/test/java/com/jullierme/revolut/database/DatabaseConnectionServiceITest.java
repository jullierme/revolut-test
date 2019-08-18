package com.jullierme.revolut.database;

import com.jullierme.revolut.config.integration.extension.commonsConfigurations.BasicConfigurationIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

@BasicConfigurationIntegrationTest
@DisplayName("Test suite of the class: DatabaseConnectionService")
class DatabaseConnectionServiceITest {

    @Test
    @DisplayName("Should return a new Database Opened Connection without auto commit")
    void givenDatabaseConnectionFactory_whenGetConnection_thenShouldReturnConnection() throws SQLException {
        //given
        DatabaseConnectionService service = DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService();

        //when
        Connection connection = service.getConnection();

        //then
        Assertions.assertNotNull(connection);
        Assertions.assertFalse(connection.isClosed());
        Assertions.assertFalse(connection.getAutoCommit());
    }
}
