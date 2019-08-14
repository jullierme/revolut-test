package com.jullierme.revolut.business.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountCreateServiceFactoryTest {
    @Test
    void whenGetInstance_shouldSucceed() {
        AccountCreateService service = AccountCreateServiceFactory.getInstance();

        Assertions.assertNotNull(service);
    }
}
