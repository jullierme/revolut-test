package com.jullierme.revolut.business.account;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static AccountCreateService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    public AccountCreateServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
    }

    public AccountCreateService getInstance() {
        if (instance == null) {
            instance = new AccountCreateServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return instance;
    }
}
