package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class TransactionCreateServiceFactory {
    private static TransactionCreateServiceFactory factoryInstance;
    private static TransactionCreateService transactionCreateService;

    public static TransactionCreateServiceFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new TransactionCreateServiceFactory();
        }

        return factoryInstance;
    }

    public TransactionCreateService getTransactionCreateService() {
        if (transactionCreateService == null) {
            transactionCreateService = new TransactionCreateServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService(),
                    TransactionFindServiceFactory.getInstance().getTransactionFindByIdService(),
                    AccountFindServiceFactory.getInstance().getAccountFindByAccountService()
            );
        }

        return transactionCreateService;
    }
}
