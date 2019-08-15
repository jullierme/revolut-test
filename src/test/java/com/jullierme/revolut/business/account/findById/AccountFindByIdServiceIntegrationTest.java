package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
class AccountFindByIdServiceIntegrationTest {
    private AccountCreateService accountCreateService;
    private AccountFindByIdService accountFindByIdService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        accountCreateService = new AccountCreateServiceFactory().getInstance();
        accountFindByIdService = new AccountFindByIdServiceFactory().getInstance();
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
    void givenANewAccount_thenFindById_shouldSucceed() throws SQLException {
        Account account = getDefaultAccout("12345656");

        account = accountCreateService.create(account);

        assertNotNull(account);
        assertNotNull(account.getId());

        Long id = account.getId();

        account = accountFindByIdService.find(id).orElse(null);

        assertNotNull(account);
        assertEquals(id, account.getId());
    }
}