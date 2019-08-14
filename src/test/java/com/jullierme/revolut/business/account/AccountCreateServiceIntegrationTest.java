package com.jullierme.revolut.business.account;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.Account;
import com.jullierme.revolut.model.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
public class AccountCreateServiceIntegrationTest {

    private AccountCreateService accountCreateService;

    @BeforeEach
    void beforeEach() {
        accountCreateService = AccountCreateServiceFactory.getInstance();
    }

    @Test
    void givenANewAccount_thenCreate_shouldSucceed() {
        Account account = AccountBuilder
                .builder()
                .accountNumber("12365478")
                .sortCode("987654")
                .name("Jullierme Barros")
                .balance(new BigDecimal(1000))
                .build();

        account = accountCreateService.create(account);

        assertNotNull(account);
        assertNotNull(account.getId());
    }
}
