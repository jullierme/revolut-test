package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountFindByIdServiceFactory {
    private static AccountFindByIdService findByIdServiceInstance;
    private static AccountFindByIdRestResponseService findByIdRestResponseInstance;

    private DatabaseConnectionServiceFactory databaseConnectionServiceFactory;

    public AccountFindByIdServiceFactory() {
        createServices();
    }

    private void createServices() {
        if(findByIdServiceInstance == null || findByIdRestResponseInstance == null) {
            databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        }
    }

    public AccountFindByIdService getFindByIdServiceInstance() {
        if (findByIdServiceInstance == null) {
            findByIdServiceInstance = new AccountFindByIdServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return findByIdServiceInstance;
    }

    public AccountFindByIdRestResponseService getFindByIdRestResponseInstance() {
        if (findByIdRestResponseInstance == null) {
            findByIdRestResponseInstance = new AccountFindByIdServiceImpl(databaseConnectionServiceFactory.getInstance());
        }

        return findByIdRestResponseInstance;
    }
}
