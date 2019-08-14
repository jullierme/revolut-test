package com.jullierme.revolut.business.account.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountCreateServiceFactoryTest {
    private AccountCreateServiceFactory accountCreateServiceFactory;

    @BeforeEach
    void init() {
        accountCreateServiceFactory = new AccountCreateServiceFactory();
    }

    @Test
    void whenGetInstance_shouldSucceed() {
        AccountCreateService service = accountCreateServiceFactory.getInstance();

        assertNotNull(service);
    }
}
