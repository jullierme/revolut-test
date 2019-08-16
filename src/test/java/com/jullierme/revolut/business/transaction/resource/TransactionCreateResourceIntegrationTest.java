package com.jullierme.revolut.business.transaction.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@ServerIntegrationTest
class TransactionCreateResourceIntegrationTest {

    TransactionRequest getDefaultTransaction() {
        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom("18181818")
                .sortCodeFrom("969696")
                .accountNumberTo("17171717")
                .sortCodeTo("959595")
                .amount(new BigDecimal(1))
                .build();
    }

    @Test
    void givenANewTransaction_whenMakingPostRequest_thenCreate() {
        TransactionRequest transactionRequest = getDefaultTransaction();

        given()
                .contentType("application/json")
                .body(transactionRequest)
                .when()
                .post("/api/transaction")
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.CREATED_201);
    }

    @Test
    void givenANewTransaction_whenMakingPostRequestWithoutFound_thenBadRequest() {
        TransactionRequest transactionRequest = getDefaultTransaction();
        transactionRequest.setAmount(new BigDecimal(99999999999L));

        given()
                .contentType("application/json")
                .body(transactionRequest)
                .when()
                .post("/api/transaction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST_400);
    }
}
