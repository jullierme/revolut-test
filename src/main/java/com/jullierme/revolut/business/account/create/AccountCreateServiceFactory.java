package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.findById.AccountFindByIdServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static AccountCreateService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;
    private AccountFindByIdServiceFactory accountFindByIdServiceFactory;

    public AccountCreateServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        accountFindByIdServiceFactory = new AccountFindByIdServiceFactory();
    }

    public AccountCreateService getInstance() {
        if (instance == null) {
            instance = new AccountCreateServiceImpl(
                    databaseConnectionServiceFactory.getInstance(),
                    accountFindByIdServiceFactory.getInstance()
            );
        }

        return instance;
    }
}
