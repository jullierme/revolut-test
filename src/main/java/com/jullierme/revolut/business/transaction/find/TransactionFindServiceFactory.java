package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class TransactionFindServiceFactory {
    private static TransactionFindByIdService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    public TransactionFindServiceFactory() {
        createServices();
    }

    private void createServices() {
        if(instance == null) {
            databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        }
    }

    public TransactionFindByIdService getInstance() {
        if (instance == null) {
            instance = new TransactionFindByIdServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return instance;
    }
}
