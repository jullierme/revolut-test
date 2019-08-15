package com.jullierme.revolut.business.account.update;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.Account;
import com.jullierme.revolut.model.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DatabaseIntegrationTest
class AccountUpdateServiceIntegrationTest {

    private AccountUpdateService accountUpdateService;
    private AccountCreateService accountCreateService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        accountUpdateService = new AccountUpdateServiceFactory().getInstance();
        accountCreateService = new AccountCreateServiceFactory().getInstance();
    }

    Account getDefaultAccout(String accountNumber) {
        return AccountBuilder
                .builder()
                .accountNumber(accountNumber)
                .sortCode("987654")
                .name("Jullierme Barros")
                .balance(new BigDecimal(1000))
                .build();
    }

    @Test
    void givenANewAccount_thenUpdate_shouldSucceed() throws SQLException {
        Account account = getDefaultAccout("16976438");

        account = accountCreateService.create(account);

        assertNotNull(account);
        assertNotNull(account.getId());

        account.setName("Lorem Ipsum");

        account = accountUpdateService.update(account.getId(), account);

        assertNotNull(account);
        assertEquals("Lorem Ipsum", account.getName());
    }

    @Test
    void givenANewAccount_thenUpdateWithInvalidId_shouldSucceed() throws SQLException {
        final Account account = accountCreateService.create(getDefaultAccout("7889188"));

        assertNotNull(account);
        assertNotNull(account.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            accountUpdateService.update(null, account);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            accountUpdateService.update(1L, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            accountUpdateService.update(account.getId() + 1, account);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            account.setId(-1L);
            accountUpdateService.update(1L, account);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            account.setId(null);
            accountUpdateService.update(1L, account);
        });
    }
}
