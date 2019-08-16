package com.jullierme.revolut.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseConnectionServiceFactoryTest {
    @Test
    void whenGetANewInstance_shouldSucceed() {
        DatabaseConnectionService service = DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService();

        assertNotNull(service);
    }
}
