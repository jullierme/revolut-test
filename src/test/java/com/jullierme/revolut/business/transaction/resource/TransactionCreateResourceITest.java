package com.jullierme.revolut.business.transaction.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import io.restassured.http.ContentType;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ServerIntegrationTest
@DisplayName("Test suite of the class: TransactionCreateResource")
class TransactionCreateResourceITest {

    TransactionRequest dummyTransaction() {
        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom(18181818)
                .accountNumberTo(17171717)
                .amount(ONE)
                .build();
    }

    @Test
    @DisplayName("Should transfer amount from one account to another")
    void givenTransactionRequest_whenPostRequest_thenShouldTransferAmountToAnotherAccount() {
        TransactionRequest transactionRequest = dummyTransaction();

        given()
                .contentType(ContentType.JSON)
                .body(transactionRequest)
                .when()
                .post("/api/transaction")
                .then()
                .statusCode(HttpStatus.CREATED_201);
    }

    @Test
    @DisplayName("Should NOT transfer amount from one account without balance")
    void givenTransactionRequest_whenPostRequest_thenShouldNotTransferAmountWithoutBalance() {
        TransactionRequest transactionRequest = dummyTransaction();
        transactionRequest.setAmount(new BigDecimal(99999999999L));

        given()
                .contentType(ContentType.JSON)
                .body(transactionRequest)
                .when()
                .post("/api/transaction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST_400);
    }

    @ParameterizedTest
    @MethodSource("invalidParametersToTransferAmountTransaction")
    @DisplayName("Should invalid parameters amount from one account without balance")
    void givenNewTransaction_whenMakingPostRequestWithInvalidParameters_thenBadRequest(
            Integer accountNumberFrom,
            Integer accountNumberTo,
            BigDecimal amount
    ) {
        TransactionRequest transactionRequest = TransactionRequestBuilder
                .builder()
                .accountNumberFrom(accountNumberFrom)
                .accountNumberTo(accountNumberTo)
                .amount(amount)
                .build();

        given()
                    .contentType(ContentType.JSON)
                    .body(transactionRequest)
                .when()
                    .post("/api/transaction")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST_400);
    }

    private static Stream<Arguments> invalidParametersToTransferAmountTransaction() {
        return Stream.of(
                arguments(18181818, 17171717, null),
                arguments(18181818, null, TEN),
                arguments(null, 17171717, TEN)
        );
    }
}
