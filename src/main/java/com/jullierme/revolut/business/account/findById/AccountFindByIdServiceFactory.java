package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountFindByIdServiceFactory {
    private static AccountFindByIdService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    public AccountFindByIdServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
    }

    public AccountFindByIdService getInstance() {
        if (instance == null) {
            instance = new AccountFindByIdServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return instance;
    }
}
