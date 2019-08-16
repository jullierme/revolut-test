package com.jullierme.revolut.business.transaction.create;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionCreateServiceFactoryTest {

    @Test
    void whenGetInstance_shouldSucceed() {
        TransactionCreateService service = TransactionCreateServiceFactory.getInstance().getTransactionCreateService();

        assertNotNull(service);
    }
}
