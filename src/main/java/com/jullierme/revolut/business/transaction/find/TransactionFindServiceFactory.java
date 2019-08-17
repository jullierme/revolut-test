package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class TransactionFindServiceFactory {
    private static TransactionFindByIdService transactionFindByIdService;
    private static TransactionFindServiceFactory factoryService;

    public TransactionFindByIdService getTransactionFindByIdService() {
        if (transactionFindByIdService == null) {
            transactionFindByIdService = new TransactionFindByIdServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService());
        }

        return transactionFindByIdService;
    }

    public static TransactionFindServiceFactory getInstance() {
        if (factoryService == null) {
            factoryService = new TransactionFindServiceFactory();
        }

        return factoryService;
    }
}
