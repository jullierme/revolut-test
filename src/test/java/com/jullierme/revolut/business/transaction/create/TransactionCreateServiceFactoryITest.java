package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: TransactionCreateServiceFactory")
class TransactionCreateServiceFactoryITest {

    @Test
    @DisplayName("Should return a new TransactionCreateService instance")
    void givenTransactionCreateServiceFactory_whenGetTransactionCreateService_thenShouldReturnNewInstance() {
        //given
        TransactionCreateServiceFactory factory = TransactionCreateServiceFactory.instance();

        //when
        TransactionCreateService service = factory.getTransactionCreateService();

        //then
        assertNotNull(service);
    }
}
