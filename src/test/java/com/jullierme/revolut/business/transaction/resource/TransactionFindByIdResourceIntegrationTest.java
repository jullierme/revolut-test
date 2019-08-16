package com.jullierme.revolut.business.transaction.resource;

import com.jullierme.revolut.business.transaction.create.TransactionCreateService;
import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceFactory;
import com.jullierme.revolut.business.transaction.find.TransactionFindByIdService;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ServerIntegrationTest
class TransactionFindByIdResourceIntegrationTest {
    private TransactionCreateService transactionCreateService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        transactionCreateService = new TransactionCreateServiceFactory().getInstance();
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
    void givenAnId_whenMakingGetRequestUsingFindById_then200Code() throws SQLException {
        Transaction transaction = addNewTransaction();

        given().when().get("/api/transaction/" + transaction.getId())
                .then()
                .statusCode(HttpStatus.OK_200);
    }

    @Test
    void givenAnInvalidId_whenMakingGetRequestUsingFindById_thenGetNotFoundCode() {
        given().when().get("/api/transaction/99999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND_404);
    }
}
