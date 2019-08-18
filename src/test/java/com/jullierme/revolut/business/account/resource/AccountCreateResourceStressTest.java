package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.config.integration.extension.server.ServerIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static java.math.BigDecimal.TEN;

@ServerIntegrationTest
@DisplayName("Test suite of the class: AccountCreateResource")
class AccountCreateResourceStressTest {

    Account dummyAccout() {
        return AccountBuilder
                .builder()
                .name("Jullierme Silva Barros")
                .balance(TEN)
                .build();
    }

    @Test
    @DisplayName("Should create accounts in parallel ")
    void givenAccount_whenPostRequest_thenShouldCreateAccount() {
        Account account = dummyAccout();

        IntStream.range(1, 1000).parallel().forEach(value -> {
            given()
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(account)
                    .when()
                    .post("/api/account")
                    .then()
                    .statusCode(HttpStatus.CREATED_201);
        });
    }


}
