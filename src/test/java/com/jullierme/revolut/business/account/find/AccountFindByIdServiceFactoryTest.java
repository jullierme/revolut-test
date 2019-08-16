package com.jullierme.revolut.business.account.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountFindByIdServiceFactoryTest {
    private AccountFindServiceFactory accountFindServiceFactory;

    @BeforeEach
    void init() {
        accountFindServiceFactory = new AccountFindServiceFactory();
    }

    @Test
    void whenGetFindByIdService_shouldSucceed() {
        AccountFindByIdService service = accountFindServiceFactory.getAccountFindByIdService();

        assertNotNull(service);
    }
}
