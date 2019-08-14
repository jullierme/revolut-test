package com.jullierme.revolut.business.account.update;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountUpdateServiceFactory {
    private static AccountUpdateService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    public AccountUpdateServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
    }

    public AccountUpdateService getInstance() {
        if (instance == null) {
            instance = new AccountUpdateServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return instance;
    }
}
