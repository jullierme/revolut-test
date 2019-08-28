package com.jullierme.revolut.business.transaction.resource;

import com.jullierme.revolut.business.transaction.create.TransactionCreateService;
import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceFactory;
import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static java.math.BigDecimal.ONE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ServerIntegrationTest
@DisplayName("Test suite of the class: TransactionFindByIdResource")
class TransactionFindByIdResourceITest {
    private TransactionCreateService transactionCreateService;

    @BeforeEach
    void beforeEach() {
        transactionCreateService = TransactionCreateServiceFactory.getInstance();
    }

    TransactionRequest dummyTransaction() {
        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom(18181818L)
                .accountNumberTo(17171717L)
                .amount(ONE)
                .build();
    }

    Transaction addNewTransaction() throws SQLException {
        TransactionRequest request = dummyTransaction();

        Transaction transaction = transactionCreateService.create(request);

        assertNotNull(transaction);
        assertNotNull(transaction.getId());

        return transaction;
    }

    @Test
    @DisplayName("Should find transaction by id")
    void givenTransaction_whenGetRequestUsingFindById_thenShouldReturnTransaction() throws SQLException {
        Transaction transaction = addNewTransaction();

        given()
                .when()
                .get("/api/transaction/" + transaction.getId())
                .then()
                .body("id", is(transaction.getId().intValue()))
                //.body("fromAccountId", is(transaction.getFromAccountId().longValue()))
                //.body("toAccountId", is((transaction.getToAccountId().longValue())))
                .body("fromAccountId", notNullValue())
                .body("toAccountId", notNullValue())
                .body("amount", is(transaction.getAmount()))
                .statusCode(HttpStatus.OK_200);
    }

    @Test
    @DisplayName("Should NOT accept invalid transaction id when finding")
    void givenAnInvalidId_whenMakingGetRequestUsingFindById_thenGetNotFoundCode() {
        given().when().get("/api/transaction/9999999999999999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND_404);
    }
}
