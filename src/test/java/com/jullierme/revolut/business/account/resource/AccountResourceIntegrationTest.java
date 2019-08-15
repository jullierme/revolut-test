package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import io.restassured.RestAssured;
import org.eclipse.jetty.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@ServerIntegrationTest
class AccountResourceIntegrationTest {

    @Test
    void givenAnId_whenMakingGetRequestUsingFindById_then200Code() {
        given().when().get("/api/account/1")
                .then()
                .statusCode(HttpStatus.OK_200);
    }

    @Test
    void givenAnId_whenMakingGetRequestUsingFindById_thenGetAnAccount() {
        given().when().get("/api/account/1")
                .then()
                .log()
                .body()
                .body("name", equalTo("Jullierme Barros"))
                .body("accountNumber",equalTo("18181818"))
                .body("sortCode",equalTo("969696"))
                .body("balance", is(1000.00f))
                .statusCode(HttpStatus.OK_200);
    }

    @Test
    void givenAnInvalidId_whenMakingGetRequestUsingFindById_thenGetNotFoundCode() {
        given().when().get("/api/account/99999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND_404);
    }
}
