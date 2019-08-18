package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static java.math.BigDecimal.TEN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ServerIntegrationTest
@DisplayName("Test suite of the class: AccountCreateResource")
class AccountCreateResourceITest {

    Account dummyAccout() {
        return AccountBuilder
                .builder()
                .name("Jullierme Silva Barros")
                .balance(TEN)
                .build();
    }

    @Test
    @DisplayName("Should create an account ")
    void givenAccount_whenPostRequest_thenShouldCreateAccount() {
        Account account = dummyAccout();

        Response response = given()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(account)
                .when()
                .post("/api/account")
                .then()
                .statusCode(HttpStatus.CREATED_201)
                .extract().response();

        String location = response.getHeader("Location");

        location = location.substring(location.indexOf("/api/account"));

        given()
                .when()
                .get(location)
                .then()
                .body("name", equalTo(account.getName()))
                //.body("balance", is(equalTo(account.getBalance())))
                .body("id", notNullValue())
                .statusCode(HttpStatus.OK_200);
    }

    @ParameterizedTest
    @MethodSource("invalidParametersToCreateAccount")
    @DisplayName("Should NOT accept invalid parameters when creating")
    void givenInvalidParameters_whenTransferFrom_thenShouldThrowException(
            String name,
            BigDecimal balance) {

        Account account = AccountBuilder
                .builder()
                .name(name)
                .balance(balance)
                .build();
        given()
                .contentType(ContentType.JSON)
                .body(account)
                .when()
                .post("/api/account")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST_400);
    }

    private static Stream<Arguments> invalidParametersToCreateAccount() {
        return Stream.of(
                arguments("JSB Name", null),
                arguments(null, TEN)
        );
    }
}
