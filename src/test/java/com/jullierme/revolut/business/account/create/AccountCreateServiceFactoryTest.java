package com.jullierme.revolut.business.account.create;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountCreateServiceFactoryTest {

    @Test
    void whenGetInstance_shouldSucceed() {
        AccountCreateService service = AccountCreateServiceFactory.getInstance().getAccountCreateServiceInstance();

        assertNotNull(service);
    }
}
