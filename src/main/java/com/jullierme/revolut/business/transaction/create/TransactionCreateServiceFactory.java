package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class TransactionCreateServiceFactory {
    private static TransactionCreateService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;
    private TransactionFindServiceFactory transactionFindServiceFactory;
    private AccountFindServiceFactory accountFindServiceFactory;

    public TransactionCreateServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        transactionFindServiceFactory = new TransactionFindServiceFactory();
        accountFindServiceFactory = new AccountFindServiceFactory();
    }

    public TransactionCreateService getInstance() {
        if (instance == null) {
            instance = new TransactionCreateServiceImpl(
                    databaseConnectionServiceFactory.getInstance(),
                    transactionFindServiceFactory.getInstance(),
                    accountFindServiceFactory.getAccountFindByAccountService()
            );
        }

        return instance;
    }
}
