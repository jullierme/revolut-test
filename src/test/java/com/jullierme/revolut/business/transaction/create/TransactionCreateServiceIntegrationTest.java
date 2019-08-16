package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.find.AccountFindByIdService;
import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;
import com.jullierme.revolut.model.transaction.TransactionRequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
class TransactionCreateServiceIntegrationTest {

    private TransactionCreateService transactionCreateService;
    private AccountFindByIdService accountFindByIdService;


    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        transactionCreateService = TransactionCreateServiceFactory.getInstance().getTransactionCreateService();
        accountFindByIdService = AccountFindServiceFactory.getInstance().getAccountFindByIdService();
    }

    TransactionRequest getDefaultTransactionRequest() {
        return TransactionRequestBuilder
                .builder()
                .accountNumberFrom("18181818")
                .sortCodeFrom("969696")
                .accountNumberTo("17171717")
                .sortCodeTo("959595")
                .amount(new BigDecimal(1000))
                .build();
    }

    @Test
    void givenANewTransaction_thenCreate_shouldSucceed() throws SQLException {
        TransactionRequest request = getDefaultTransactionRequest();

        Transaction transaction = transactionCreateService.create(request);

        assertNotNull(transaction);
        assertNotNull(transaction.getId());

        Account accountFrom = accountFindByIdService.find(1L).orElse(null);

        assertNotNull(accountFrom);

        Account accountTo = accountFindByIdService.find(2L).orElse(null);

        assertNotNull(accountTo);

        System.out.println(accountFrom.getBalance());
        System.out.println(accountTo.getBalance());
    }
}
