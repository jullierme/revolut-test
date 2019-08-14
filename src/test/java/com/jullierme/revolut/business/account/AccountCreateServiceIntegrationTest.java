package com.jullierme.revolut.business.account;

import com.google.inject.Inject;
import com.jullierme.revolut.config.guice.GuiceService;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@DatabaseIntegrationTest
public class AccountCreateServiceIntegrationTest {

    /*@Mock
    DatabaseConnectionService databaseConnectionService;*/

    //@InjectMocks
    private AccountCreateService accountCreateService;

    @BeforeEach
    void beforeEach() {
        accountCreateService = GuiceService.injector.getInstance(AccountCreateService.class);
    }

    @Test
    void givenANewAccount_thenCreate_shouldSucceed() {
        Account account = Account
                .builder()
                .accountNumber("12365478")
                .sortCode("987654")
                .name("Jullierme Barros")
                .balance(new BigDecimal(1000))
                .build();

        account = accountCreateService.create(account);

        assertNotNull(account);
        assertNotNull(account.getId());

        System.out.println(account.getId());
    }
}
