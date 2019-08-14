package com.jullierme.revolut.business.account;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static AccountCreateService instance;

    public static AccountCreateService getInstance() {
        if (instance == null) {
            instance = new AccountCreateServiceImpl(DatabaseConnectionServiceFactory.getInstance());
        }

        return instance;
    }
}
