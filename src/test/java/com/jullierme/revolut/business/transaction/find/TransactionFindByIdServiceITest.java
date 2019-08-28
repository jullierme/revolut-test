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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: TransactionFindByIdService")
class TransactionFindByIdServiceITest {
    private TransactionFindByIdService transactionFindByIdService;
    private TransactionCreateService transactionCreateService;

    @BeforeEach
    void setup() {
        transactionFindByIdService = TransactionFindServiceFactory.getInstance();
        transactionCreateService = TransactionCreateServiceFactory.getInstance();
    }


    @Test
    @DisplayName("Should find transaction by id")
    void givenExistentTransaction_whenFind_thenShouldFindTransaction() throws SQLException {
        //given
        Transaction transactionSaved = transactionCreateService.create(dummyTransaction());
        assertNotNull(transactionSaved);

        //when
        Transaction accountFound = transactionFindByIdService
                .find(transactionSaved.getId())
                .orElse(null);

        //then
        assertNotNull(accountFound);
        assertEquals(transactionSaved.getId(), accountFound.getId());
    }

    @ParameterizedTest
    @MethodSource("invalidParametersToFind")
    @DisplayName("Should NOT accept invalid transaction id when finding")
    void givenNonexistentTransaction_whenFind_thenShouldNotFound(Long id) {
        //given parameter

        //when
        Transaction account = transactionFindByIdService
                .find(id)
                .orElse(null);

        //then
        assertNull(account);
    }

    private static Stream<Arguments> invalidParametersToFind(){
        Long NULL = null;

        return Stream.of(
                arguments(99999999L),
                arguments(NULL)
        );
    }

    private TransactionRequest dummyTransaction() {
        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom(18181818L)
                .accountNumberTo(17171717L)
                .amount(ONE)
                .build();
    }
}