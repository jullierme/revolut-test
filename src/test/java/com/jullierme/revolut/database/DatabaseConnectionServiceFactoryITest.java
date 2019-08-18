package com.jullierme.revolut.database;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: DatabaseConnectionServiceFactory")
class DatabaseConnectionServiceFactoryITest {

    @Test
    @DisplayName("Should return a new TransactionFindByIdService instance")
    void givenDatabaseConnectionServiceFactory_whenGetTransactionFindByIdService_thenShouldReturnNewInstance() {
        //given
        DatabaseConnectionServiceFactory factory = DatabaseConnectionServiceFactory.getInstance();

        //when
        DatabaseConnectionService service = factory.getDatabaseConnectionService();

        //then
        assertNotNull(service);
    }
}
