package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static AccountCreateServiceFactory factoryIntance;
    private static AccountCreateService accountCreateService;

    private AccountCreateServiceFactory(){}

    public static AccountCreateServiceFactory instance() {
        if (factoryIntance == null) {
            factoryIntance = new AccountCreateServiceFactory();
        }

        return factoryIntance;
    }

    public AccountCreateService getAccountCreateService() {
        if (accountCreateService == null) {
            accountCreateService = new AccountCreateServiceImpl(
                    DatabaseConnectionServiceFactory.getInstance().getDatabaseConnectionService(),
                    AccountFindServiceFactory.instance().getAccountFindByIdService()
            );
        }

        return accountCreateService;
    }
}
