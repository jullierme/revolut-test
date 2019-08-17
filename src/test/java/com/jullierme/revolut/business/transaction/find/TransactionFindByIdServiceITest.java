package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.business.transaction.create.TransactionCreateService;
import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceFactory;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: TransactionFindByIdService")
class TransactionFindByIdServiceITest {
    private TransactionFindByIdService transactionFindByIdService;
    private TransactionCreateService transactionCreateService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        transactionFindByIdService = TransactionFindServiceFactory.getInstance().getTransactionFindByIdService();
        transactionCreateService = TransactionCreateServiceFactory.getInstance().getTransactionCreateService();
    }

    TransactionRequest getDefaultTransactionRequest() {
        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom("18181818")
                .sortCodeFrom("969696")
                .accountNumberTo("17171717")
                .sortCodeTo("959595")
                .amount(new BigDecimal(1))
                .build();
    }

    Transaction addNewTransaction() throws SQLException {
        TransactionRequest request = getDefaultTransactionRequest();

        Transaction transaction = transactionCreateService.create(request);

        assertNotNull(transaction);
        assertNotNull(transaction.getId());

        return transaction;
    }

    @Test
    void givenAnExistentTransaction_thenFindById_shouldSucceed() throws SQLException {
        Transaction transaction = addNewTransaction();

        Long id = transaction.getId();

        transaction = transactionFindByIdService.find(id).orElse(null);

        assertNotNull(transaction);
        assertEquals(id, transaction.getId());
    }

    @Test
    void givenANonexistentTransaction_thenFindById_shouldNotFound() {
        Transaction transaction = transactionFindByIdService
                .find(null)
                .orElse(null);

        assertNull(transaction);
    }
}