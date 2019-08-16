package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionCreateServiceFactoryTest {
    private TransactionCreateServiceFactory transactionCreateServiceFactory;

    @BeforeEach
    void init() {
        transactionCreateServiceFactory = new TransactionCreateServiceFactory();
    }

    @Test
    void whenGetInstance_shouldSucceed() {
        TransactionCreateService service = transactionCreateServiceFactory.getInstance();

        assertNotNull(service);
    }
}
