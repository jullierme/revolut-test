package com.jullierme.revolut.business.account.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountFindByAccountServiceFactoryTest {
    private AccountFindServiceFactory accountFindServiceFactory;

    @BeforeEach
    void init() {
        accountFindServiceFactory = new AccountFindServiceFactory();
    }

    @Test
    void whenGetFindByAccountService_shouldSucceed() {
        AccountFindByAccountService service = accountFindServiceFactory.getAccountFindByAccountService();

        assertNotNull(service);
    }
}
