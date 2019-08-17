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
@DisplayName("Test suite of the class: AccountFindByIdService")
class AccountFindByIdServiceITest {

    private AccountFindByIdService accountFindByIdService;
    private AccountCreateService accountCreateService;

    @BeforeEach
    void setup() {
        accountFindByIdService = AccountFindServiceFactory.instance().getAccountFindByIdService();
        accountCreateService = AccountCreateServiceFactory.instance().getAccountCreateService();
    }

    @Test
    @DisplayName("Should find account by id")
    void givenExistentAccount_whenFind_thenShouldFindAccount() throws SQLException {
        //given
        Account accountSaved = accountCreateService.create(dummyAccount());
        assertNotNull(accountSaved);

        //when
        Account accountFound = accountFindByIdService
                .find(accountSaved.getId())
                .orElse(null);

        //then
        assertNotNull(accountFound);
        assertEquals(accountSaved.getId(), accountFound.getId());
    }

    @Test
    @DisplayName("Should NOT accept invalid account id when finding")
    void givenNonexistentAccount_whenFind_thenShouldNotFound() {
        //given
        Long id = 99999999L;

        //when
        Account account = accountFindByIdService
                .find(id)
                .orElse(null);

        //then
        assertNull(account);
    }

    private Account dummyAccount() {
        return AccountBuilder
                .builder()
                .name("JSB ASDF")
                .balance(TEN)
                .build();

    }
}