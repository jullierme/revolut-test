package com.jullierme.revolut.business.account.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountUpdateServiceFactoryTest {
    private AccountUpdateServiceFactory accountUpdateServiceFactory;

    @BeforeEach
    void init() {
        accountUpdateServiceFactory = new AccountUpdateServiceFactory();
    }

    @Test
    void whenGetInstance_shouldSucceed() {
        AccountUpdateService service = accountUpdateServiceFactory.getInstance();

        assertNotNull(service);
    }
}
