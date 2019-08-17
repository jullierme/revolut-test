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
@DisplayName("Test suite of the class: AccountFindByIdService")
class AccountFindByIdServiceITest {
    private AccountFindByIdService accountFindByIdService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        accountFindByIdService = new AccountFindServiceFactory().getAccountFindByIdService();
    }

    @Test
    void givenAnExistentAccount_thenFindById_shouldSucceed() {
        Account account = accountFindByIdService.find(1L).orElse(null);

        assertNotNull(account);
        assertEquals(1L, account.getId());
    }

    @Test
    void givenANonexistentAccount_thenFindById_shouldNotFound() {
        Account account = accountFindByIdService
                .find(989898L)
                .orElse(null);

        assertNull(account);
    }
}