package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static AccountCreateService instance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;
    private AccountFindServiceFactory accountFindServiceFactory;

    public AccountCreateServiceFactory() {
        createServices();
    }

    private void createServices() {
        databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        accountFindServiceFactory = new AccountFindServiceFactory();
    }

    public AccountCreateService getInstance() {
        if (instance == null) {
            instance = new AccountCreateServiceImpl(
                    databaseConnectionServiceFactory.getInstance(),
                    accountFindServiceFactory.getAccountFindByIdService()
            );
        }

        return instance;
    }
}
