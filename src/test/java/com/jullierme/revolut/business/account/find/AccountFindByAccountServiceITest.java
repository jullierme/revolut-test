package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountFindByAccountService")
class AccountFindByAccountServiceITest {
    private AccountFindByAccountService accountFindByAccountService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        accountFindByAccountService = new AccountFindServiceFactory().getAccountFindByAccountService();
    }

    @Test
    void givenAnExistentAccount_thenFindByAccount_shouldSucceed() {
        Account account = accountFindByAccountService
                .find("18181818", "969696")
                .orElse(null);

        assertNotNull(account);
        assertEquals(1L, account.getId());

        account = accountFindByAccountService
                .find("17171717", "959595")
                .orElse(null);

        assertNotNull(account);
        assertEquals(2L, account.getId());
    }

    @Test
    void givenANonexistentAccount_thenFindByAccount_shouldNotFound() {
        Account account = accountFindByAccountService
                .find("0000000", "969696")
                .orElse(null);

        assertNull(account);
    }
}