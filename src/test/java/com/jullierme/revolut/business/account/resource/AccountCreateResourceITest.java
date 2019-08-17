package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@ServerIntegrationTest
@DisplayName("Test suite of the class: AccountCreateResource")
class AccountCreateResourceITest {

    Account getDefaultAccout() {
        return AccountBuilder
                .builder()
                .accountNumber("12343132")
                .sortCode("568956")
                .name("Jullierme Silva Barros")
                .balance(new BigDecimal(10000))
                .build();
    }

    @Test
    void givenANewAccount_whenMakingPostRequest_thenCreate() {
        Account account = getDefaultAccout();

        given()
                .contentType("application/json")
                .body(account)
                .when()
                .post("/api/account")
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.CREATED_201);
    }

    @Test
    void givenANewAccount_whenMakingPostRequestTwoTimes_thenBadRequest() {
        Account account = getDefaultAccout();
        account.setAccountNumber("11122233");

        given()
                .contentType("application/json")
                .body(account)
                .when()
                .post("/api/account")
                .then()
                .statusCode(HttpStatus.CREATED_201);


        given()
                .contentType("application/json")
                .body(account)
                .when()
                .post("/api/account")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST_400);
    }
}
