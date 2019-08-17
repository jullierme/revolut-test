package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class TransactionFindServiceFactory {
    private static TransactionFindByIdService transactionFindByIdService;
    private static TransactionFindServiceFactory factoryService;

    private TransactionFindServiceFactory(){}

    public static TransactionFindServiceFactory instance() {
        if (factoryService == null) {
            factoryService = new TransactionFindServiceFactory();
        }

        return factoryService;
    }

    public TransactionFindByIdService getTransactionFindByIdService() {
        if (transactionFindByIdService == null) {
            transactionFindByIdService = new TransactionFindByIdServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService());
        }

        return transactionFindByIdService;
    }
}
