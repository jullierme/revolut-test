package com.jullierme.revolut.business.transaction.create;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test suite of the class: TransactionCreateServiceFactory")
class TransactionCreateServiceFactoryUTest {

    @Test
    @DisplayName("Should return a new TransactionCreateService instance")
    void givenTransactionCreateServiceFactory_whenGetTransactionCreateService_thenShouldReturnNewInstance() {
        //given
        TransactionCreateServiceFactory factory = TransactionCreateServiceFactory.getInstance();

        //when
        TransactionCreateService service = factory.getTransactionCreateService();

        //then
        assertNotNull(service);
    }
}
