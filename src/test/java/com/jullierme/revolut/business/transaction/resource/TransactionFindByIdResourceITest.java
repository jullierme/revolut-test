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

import java.math.BigDecimal;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ServerIntegrationTest
@DisplayName("Test suite of the class: TransactionFindByIdResource")
class TransactionFindByIdResourceITest {
    private TransactionCreateService transactionCreateService;

    @BeforeEach
    void beforeEach() {
        transactionCreateService = TransactionCreateServiceFactory.instance().getTransactionCreateService();
    }

    TransactionRequest dummyTransaction() {
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
                .log()
                .body()
                    .body("id", is(transaction.getId().intValue()))
                    .body("fromAccountId", equalTo(transaction.getFromAccountId().intValue()))
                    .body("toAccountId", equalTo(transaction.getToAccountId().intValue()))
                    .body("amount", is(transaction.getAmount()))
                    .body("instant", notNullValue())
                    .statusCode(HttpStatus.OK_200);
    }

    @Test
    @DisplayName("Should NOT accept invalid transaction id when finding")
    void givenAnInvalidId_whenMakingGetRequestUsingFindById_thenGetNotFoundCode() {
        given().when().get("/api/transaction/99999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND_404);
    }
}
