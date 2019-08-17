package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.stream.Stream;

import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountCreateService")
class AccountCreateServiceITest {
    private AccountCreateService accountCreateService;


    @BeforeEach
    void beforeEach() {
        accountCreateService = AccountCreateServiceFactory.instance().getAccountCreateService();
    }

    @Test
    @DisplayName("Should create an account ")
    void givenAccount_whenCreate_thenShouldCreateAccount() throws SQLException {
        //given
        String name = "JSB";
        String accountNumber = "14253696";
        String sortCode = "197364";
        BigDecimal balance = new BigDecimal(123321.45).setScale(2, RoundingMode.DOWN);

        Account account = AccountBuilder
                .builder()
                .name(name)
                .accountNumber(accountNumber)
                .sortCode(sortCode)
                .balance(balance)
                .build();
        //when
        Account savedAccount = accountCreateService.create(account);

        //then
        assertNotNull(savedAccount);
        assertNotNull(savedAccount.getId());
        assertEquals(name, savedAccount.getName());
        assertEquals(accountNumber, savedAccount.getAccountNumber());
        assertEquals(sortCode, savedAccount.getSortCode());
        assertEquals(balance, savedAccount.getBalance());
    }

    @DisplayName("Should NOT accept duplicated account number when saving")
    @Test
    void givenDuplicatesAccounts_whenCreate_thenShouldThrowException() {
        //given
        Account account1 = dummyAccount();
        Account account2 = dummyAccount();
        assertNotNull(account1);

        //when
        Executable executable = () -> accountCreateService.create(account2);

        //then
        assertThrows(JdbcSQLIntegrityConstraintViolationException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("invalidParametersToCreateAccount")
    @DisplayName("Should NOT accept invalid parameters when saving")
    void givenInalidParameter_thenCreate_thenShouldThrowException(String name,
                                                                  String accountNumber,
                                                                  String sortCode,
                                                                  BigDecimal balance) {

        //given
        Account account = AccountBuilder
                .builder()
                .name(name)
                .accountNumber(accountNumber)
                .sortCode(sortCode)
                .balance(balance)
                .build();

        //when
        Executable executable = () -> accountCreateService.create(account);

        //then
        assertThrows(JdbcSQLIntegrityConstraintViolationException.class, executable);
    }

    private Account dummyAccount() {
        String name = "JSB";
        String accountNumber = "14253696";
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

    private static Stream<Arguments> invalidParametersToCreateAccount() {
        return Stream.of(
                arguments("Name", "12345678", "123456", null),
                arguments("Name", "12345678", null, ZERO),
                arguments("Name", null, "123456", ZERO),
                arguments(null, "12345678", "123456", ZERO)
        );
    }
}
