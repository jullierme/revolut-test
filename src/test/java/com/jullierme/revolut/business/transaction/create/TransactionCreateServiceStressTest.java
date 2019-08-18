package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import com.jullierme.revolut.business.account.find.AccountFindByAccountService;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.stream.IntStream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountCreateResource")
class TransactionCreateServiceStressTest {
    private TransactionCreateService transactionCreateService;
    private AccountCreateService accountCreateService;
    private AccountFindByAccountService accountFindByAccountService;

    @BeforeEach
    void setup() {
        transactionCreateService = TransactionCreateServiceFactory.instance().getTransactionCreateService();
        accountFindByAccountService = AccountFindServiceFactory.instance().getAccountFindByAccountService();
        accountCreateService = AccountCreateServiceFactory.instance().getAccountCreateService();
    }

    @Test
    @DisplayName("Should transfer all balance without error")
    void givenTwoAccounts_whenStressPostRequest_thenShouldTransferAlmostAllAmountWithoutError() throws SQLException {
        final BigDecimal initialBalance = new BigDecimal(1000);
        final BigDecimal amountToTransfer = ONE.setScale(2, RoundingMode.DOWN);

        final BigDecimal finalBalanceFrom = ONE.setScale(2, RoundingMode.DOWN);
        final BigDecimal finalBalanceTo = new BigDecimal(1999).setScale(2, RoundingMode.DOWN);

        //given
        TransactionRequest request = dummyTransactionRequest(initialBalance, amountToTransfer);

        //when
        IntStream.range(1, 1000).parallel().forEach(value -> {
            try {
                transactionCreateService.create(request);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        //then
        final Account accountFrom = accountFindByAccountService
                .findByAccount(request.getAccountNumberFrom()).orElse(null);
        assertNotNull(accountFrom);

        final Account accountTo = accountFindByAccountService
                .findByAccount(request.getAccountNumberTo()).orElse(null);
        assertNotNull(accountTo);

        assertEquals(finalBalanceTo, accountTo.getBalance());//1999
        assertEquals(finalBalanceFrom, accountFrom.getBalance());//1
    }

    @Test
    @DisplayName("Should NOT transfer from one account without enough balance")
    void givenTwoAccounts_whenStressPostRequest_thenShouldNotTransferWithoutEnoughBalance() throws SQLException {
        final BigDecimal initialBalance = new BigDecimal(10000);
        final BigDecimal amountToTransfer = TEN.setScale(2, RoundingMode.DOWN);

        final BigDecimal finalBalanceFrom = ZERO.setScale(2, RoundingMode.DOWN);
        final BigDecimal finalBalanceTo = new BigDecimal(20000).setScale(2, RoundingMode.DOWN);


        //given
        TransactionRequest request = dummyTransactionRequest(initialBalance, amountToTransfer);

        //when
        Executable transferFrom =
                () -> IntStream.range(1, 1500).parallel().forEach(value -> {
                    try {
                        transactionCreateService.create(request);
                    } catch (SQLException e) {
                       // e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });

        //then
        assertThrows(IllegalStateException.class, transferFrom);

        final Account accountFrom = accountFindByAccountService.findByAccount(request.getAccountNumberFrom()).orElse(null);
        assertNotNull(accountFrom);

        final Account accountTo = accountFindByAccountService.findByAccount(request.getAccountNumberTo()).orElse(null);
        assertNotNull(accountTo);

        assertEquals(finalBalanceFrom, accountFrom.getBalance());//zero

        assertEquals(finalBalanceTo, accountTo.getBalance());//20000
    }

    private TransactionRequest dummyTransactionRequest(BigDecimal initialBalance, BigDecimal amountToTransfer) throws SQLException {
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


        final Account accountSavedFrom = accountCreateService.create(accountFrom);
        final Account accountSavedTo = accountCreateService.create(accountTo);

        assertNotNull(accountSavedFrom);
        assertNotNull(accountSavedTo);

        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom(accountSavedFrom.getAccountNumber())
                .accountNumberTo(accountSavedTo.getAccountNumber())
                .amount(amountToTransfer)
                .build();
    }
}
