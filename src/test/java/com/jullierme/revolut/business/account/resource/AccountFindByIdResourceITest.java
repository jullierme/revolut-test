package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@ServerIntegrationTest
@DisplayName("Test suite of the class: AccountFindByIdResource")
class AccountFindByIdResourceITest {

    @Test
    @DisplayName("Should find account by id")
    void givenSavedAccount_whenGetRequest_thenShouldResultAccount() {
        given().when().get("/api/account/1")
                .then()
                .body("name", equalTo("Jullierme Barros"))
                .body("accountNumber",equalTo(18181818))
                .statusCode(HttpStatus.OK_200);
    }

    @Test
    @DisplayName("Should NOT accept invalid account id when finding")
    void givenInvalidAccount_whenGetRequest_thenShouldNotFound() {
        given().when().get("/api/account/99999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND_404);
    }
}
