package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountFindServiceFactory {
    private static AccountFindByIdService findByIdService;
    private static AccountFindByAccountService findByAccountService;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    public AccountFindServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
    }

    public AccountFindByIdService getAccountFindByIdService() {
        if (findByIdService == null) {
            findByIdService = new AccountFindByIdServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return findByIdService;
    }

    public AccountFindByAccountService getAccountFindByAccountService() {
        if (findByAccountService == null) {
            findByAccountService = new AccountFindByIdServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return findByAccountService;
    }
}
