package com.jullierme.revolut.business.transaction.find;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test suite of the class: TransactionFindServiceFactory")
class TransactionFindServiceFactoryUTest {

    @Test
    @DisplayName("Should return a new TransactionFindByIdService instance")
    void givenTransactionFindServiceFactory_whenGetTransactionFindByIdService_thenShouldReturnNewInstance() {
        //given
        TransactionFindServiceFactory factory = TransactionFindServiceFactory.getInstance();

        //when
        TransactionFindByIdService service = factory.getTransactionFindByIdService();

        //then
        assertNotNull(service);
    }
}
