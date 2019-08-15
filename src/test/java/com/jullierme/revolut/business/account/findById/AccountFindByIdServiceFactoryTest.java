package com.jullierme.revolut.business.account.findById;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountFindByIdServiceFactoryTest {
    private AccountFindByIdServiceFactory accountFindByIdServiceFactory;

    @BeforeEach
    void init() {
        accountFindByIdServiceFactory = new AccountFindByIdServiceFactory();
    }

    @Test
    void whenGetFindByIdService_shouldSucceed() {
        AccountFindByIdService service = accountFindByIdServiceFactory.getFindByIdServiceInstance();

        assertNotNull(service);
    }

    @Test
    void whenGetFindByIdRestResponseService_shouldSucceed() {
        AccountFindByIdRestResponseService service = accountFindByIdServiceFactory.getFindByIdRestResponseInstance();

        assertNotNull(service);
    }
}
