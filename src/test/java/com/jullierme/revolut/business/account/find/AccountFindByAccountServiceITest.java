package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountFindByAccountService")
class AccountFindByAccountServiceITest {
    private AccountFindByAccountService accountFindByAccountService;
    private AccountCreateService accountCreateService;

    @BeforeEach
    void setup() {
        accountFindByAccountService = AccountFindServiceFactory.instance().getAccountFindByAccountService();
        accountCreateService = AccountCreateServiceFactory.instance().getAccountCreateService();
    }

    @Test
    @DisplayName("Should find Account by Account")
    void givenExistentAccount_whenFind_thenShouldFindAccount() throws SQLException {
        //given
        Account account = dummyAccount();
        account = accountCreateService.create(account);
        assertNotNull(account);
        Integer accountNumber = account.getAccountNumber();

        //when
        Account accountFound = accountFindByAccountService
                .findByAccount(accountNumber)
                .orElse(null);

        //then
        assertNotNull(accountFound);
        assertEquals(accountNumber, accountFound.getAccountNumber());
    }

    @Test
    @DisplayName("Should NOT find Account with invalid account")
    void givenNonexistentAccount_whenFind_thenShouldNotFound() {
        //given
        Integer accountNumber = 0;

        //when
        Account account = accountFindByAccountService
                .findByAccount(accountNumber)
                .orElse(null);

        //then
        assertNull(account);
    }

    private Account dummyAccount() {
        return AccountBuilder
                .builder()
                .name("JSB")
                .balance(TEN)
                .build();

    }
}