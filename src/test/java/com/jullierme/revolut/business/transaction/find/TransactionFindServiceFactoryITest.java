package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: TransactionFindServiceFactory")
class TransactionFindServiceFactoryITest {

    @Test
    @DisplayName("Should return a new TransactionFindByIdService instance")
    void givenTransactionFindServiceFactory_whenGetTransactionFindByIdService_thenShouldReturnNewInstance() {
        //given
        TransactionFindServiceFactory factory = TransactionFindServiceFactory.instance();

        //when
        TransactionFindByIdService service = factory.getTransactionFindByIdService();

        //then
        assertNotNull(service);
    }
}
