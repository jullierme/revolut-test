package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountFindServiceFactory {
    private static AccountFindServiceFactory factoryIntance;
    private static AccountFindByIdService findByIdService;
    private static AccountFindByAccountService findByAccountService;

    private AccountFindServiceFactory(){}

    public static AccountFindServiceFactory instance() {
        if (factoryIntance == null) {
            factoryIntance = new AccountFindServiceFactory();
        }

        return factoryIntance;
    }

    public AccountFindByIdService getAccountFindByIdService() {
        if (findByIdService == null) {
            findByIdService = new AccountFindByIdServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService()
            );
        }

        return findByIdService;
    }

    public AccountFindByAccountService getAccountFindByAccountService() {
        if (findByAccountService == null) {
            findByAccountService = new AccountFindByIdServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService()
            );
        }

        return findByAccountService;
    }
}
