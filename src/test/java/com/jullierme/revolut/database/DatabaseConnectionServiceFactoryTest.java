package com.jullierme.revolut.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionServiceFactoryTest {
    @Test
    void whenGetANewInstance_shouldSucceed() {
        DatabaseConnectionService dcsf = DatabaseConnectionServiceFactory.getInstance();

        Assertions.assertNotNull(dcsf);
    }
}
