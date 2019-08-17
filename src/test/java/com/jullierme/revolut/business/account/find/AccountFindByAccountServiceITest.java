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
        String accountNumber = account.getAccountNumber();
        String sortCode = account.getSortCode();

        accountCreateService.create(account);

        //when
        Account accountFound = accountFindByAccountService
                .find(accountNumber, sortCode)
                .orElse(null);

        //then
        assertNotNull(accountFound);
        assertEquals(accountNumber, accountFound.getAccountNumber());
        assertEquals(sortCode, accountFound.getSortCode());
    }

    @Test
    @DisplayName("Should NOT find Account with invalid account")
    void givenNonexistentAccount_whenFind_thenShouldNotFound() {
        //given
        String accountNumber = "0000000";
        String sortCode = "969696";

        //when
        Account account = accountFindByAccountService
                .find(accountNumber, sortCode)
                .orElse(null);

        //then
        assertNull(account);
    }

    private Account dummyAccount() {
        String name = "JSB";
        String accountNumber = "11229988";
        String sortCode = "197364";
        BigDecimal balance = new BigDecimal(123321.45).setScale(2, RoundingMode.DOWN);

        return AccountBuilder
                .builder()
                .name(name)
                .accountNumber(accountNumber)
                .sortCode(sortCode)
                .balance(balance)
                .build();

    }
}