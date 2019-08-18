package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.stream.IntStream;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountCreateResource")
class AccountCreateServiceStressTest {
    private AccountCreateService accountCreateService;


    @BeforeEach
    void beforeEach() {
        accountCreateService = AccountCreateServiceFactory.instance().getAccountCreateService();
    }

    @Test
    @DisplayName("Should create an account ")
    void givenAccount_whenPostRequest_thenShouldCreateAccount() {
        IntStream.range(1, 10000).parallel().forEach(value -> {
            //given
            String name = "JSB";
            BigDecimal balance = TEN.setScale(2, RoundingMode.DOWN);

            Account account = AccountBuilder
                    .builder()
                    .name(name)
                    .balance(balance)
                    .build();
            //when
            Account savedAccount = null;
            try {
                savedAccount = accountCreateService.create(account);
                System.out.println(value);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //then
            assertNotNull(savedAccount);
            assertNotNull(savedAccount.getId());
            assertEquals(name, savedAccount.getName());
            assertNotNull(savedAccount.getAccountNumber());
            assertEquals(balance, savedAccount.getBalance());
        });
    }
}
