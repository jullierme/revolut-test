package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static AccountCreateServiceFactory factoryIntance;
    private static AccountCreateService accountCreateService;

    public static AccountCreateServiceFactory getInstance() {
        if (factoryIntance == null) {
            factoryIntance = new AccountCreateServiceFactory();
        }

        return factoryIntance;
    }

    public AccountCreateService getAccountCreateServiceInstance() {
        if (accountCreateService == null) {
            accountCreateService = new AccountCreateServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService(),
                    AccountFindServiceFactory.getInstance().getAccountFindByIdService()
            );
        }

        return accountCreateService;
    }
}
