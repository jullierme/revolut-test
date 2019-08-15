package com.jullierme.revolut.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseConnectionServiceFactoryTest {
    private DatabaseConnectionServiceFactory accountCreateServiceFactory;

    @BeforeEach
    void init() {
        accountCreateServiceFactory = new DatabaseConnectionServiceFactory();
    }

    @Test
    void whenGetANewInstance_shouldSucceed() {
        DatabaseConnectionService service = accountCreateServiceFactory.getInstance();

        assertNotNull(service);
    }
}
