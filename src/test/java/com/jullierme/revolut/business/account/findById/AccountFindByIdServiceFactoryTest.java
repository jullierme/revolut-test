package com.jullierme.revolut.business.account.findById;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountFindByIdServiceFactoryTest {
    private AccountFindByIdServiceFactory accountFindByIdServiceFactory;

    @BeforeEach
    void init() {
        accountFindByIdServiceFactory = new AccountFindByIdServiceFactory();
    }

    @Test
    void whenGetInstance_shouldSucceed() {
        AccountFindByIdService service = accountFindByIdServiceFactory.getInstance();

        assertNotNull(service);
    }
}
