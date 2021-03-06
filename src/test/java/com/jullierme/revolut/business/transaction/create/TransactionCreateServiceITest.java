package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import com.jullierme.revolut.business.account.find.AccountFindByIdService;
import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountBuilder;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
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

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: TransactionCreateService")
class TransactionCreateServiceITest {

    private TransactionCreateService transactionCreateService;
    private AccountCreateService accountCreateService;
    private AccountFindByIdService accountFindByIdService;


    @BeforeEach
    void setup() {
        transactionCreateService = TransactionCreateServiceFactory.getInstance();
        accountFindByIdService = AccountFindServiceFactory.getInstance();
        accountCreateService = AccountCreateServiceFactory.getInstance();
    }

    @Test
    @DisplayName("Should transfer amount from one account to another")
    void givenTransaction_whenCreate_thenShouldTransferAmountToAnotherAccount() throws SQLException {
        //given
        BigDecimal initialBalance = TEN.setScale(2, RoundingMode.DOWN);
        BigDecimal amountToTransfer = ONE.setScale(2, RoundingMode.DOWN);

        Account accountFrom = AccountBuilder
                .builder()
                .name("JSB")
                .balance(initialBalance)
                .build();

        Account accountTo = AccountBuilder
                .builder()
                .name("Manu")
                .balance(initialBalance)
                .build();


        accountFrom = accountCreateService.create(accountFrom);
        accountTo = accountCreateService.create(accountTo);

        assertNotNull(accountFrom);
        assertNotNull(accountTo);

        TransactionRequest request = TransactionRequestBuilder
                .builder()
                .accountNumberFrom(accountFrom.getId())
                .accountNumberTo(accountTo.getId())
                .amount(amountToTransfer)
                .build();

        //when
        Transaction transaction = transactionCreateService.create(request);

        //then
        assertNotNull(transaction);
        assertNotNull(transaction.getId());

        accountFrom = accountFindByIdService.find(accountFrom.getId()).orElse(null);
        assertNotNull(accountFrom);

        accountTo = accountFindByIdService.find(accountTo.getId()).orElse(null);
        assertNotNull(accountTo);

        assertEquals(initialBalance.add(amountToTransfer), accountTo.getBalance());
        assertEquals(initialBalance.subtract(amountToTransfer), accountFrom.getBalance());
    }

    @Test
    @DisplayName("Should NOT transfer from one account without enough balance")
    void givenAccountWithoutEnoughBalance_whenTransferFrom_thenShouldThrowException() throws SQLException {
        //given
        BigDecimal initialBalance = ONE.setScale(2, RoundingMode.DOWN);
        BigDecimal amountToTransfer = TEN.setScale(2, RoundingMode.DOWN);

        Account accountFrom = AccountBuilder
                .builder()
                .name("JSB")
                .balance(initialBalance)
                .build();

        Account accountTo = AccountBuilder
                .builder()
                .name("Manu")
                .balance(initialBalance)
                .build();


        accountFrom = accountCreateService.create(accountFrom);
        accountTo = accountCreateService.create(accountTo);

        assertNotNull(accountFrom);
        assertNotNull(accountTo);

        TransactionRequest request = TransactionRequestBuilder
                .builder()
                .accountNumberFrom(accountFrom.getId())
                .accountNumberTo(accountTo.getId())
                .amount(amountToTransfer)
                .build();

        //when
        Executable transferFrom =
                () ->  transactionCreateService.create(request);

        //then
        assertThrows(IllegalStateException.class, transferFrom);
    }

    @ParameterizedTest
    @MethodSource("invalidParametersToTransferFrom")
    @DisplayName("Should NOT accept invalid parameters when transferring")
    void givenInvalidParameters_whenTransferFrom_thenShouldThrowException(
            Long accountNumberFrom,
            Long accountNumberTo,
            BigDecimal amount) {
        //given parameters
        TransactionRequest transactionRequest = TransactionRequestBuilder
                .builder()
                .accountNumberFrom(accountNumberFrom)
                .accountNumberTo(accountNumberTo)
                .amount(amount)
                .build();
        //when
        Executable transferFrom =
                () ->  transactionCreateService.create(transactionRequest);

        //then
        assertThrows(IllegalArgumentException.class, transferFrom);
    }


    private static Stream<Arguments> invalidParametersToTransferFrom() {
        return Stream.of(
                arguments(18181818L, 17171717L, null),
                arguments(18181818L, null, TEN),
                arguments(null, 17171717L, TEN)
        );
    }
}
