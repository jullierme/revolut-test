package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceFactory;
import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceImpl;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountCreateServiceFactory {
    private static volatile AccountCreateService instance;

    private AccountCreateServiceFactory() {
    }

    public static AccountCreateService getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (AccountCreateServiceFactory.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new AccountCreateServiceImpl(
                            DatabaseConnectionServiceFactory.getInstance(),
                            AccountFindServiceFactory.getInstance()
                    );
                }

            }
        }

        return instance;
    }
}
