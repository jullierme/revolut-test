package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class AccountFindServiceFactory {
    private static volatile AccountFindByIdService instance;

    private AccountFindServiceFactory() {
    }

    public static AccountFindByIdService getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (AccountFindServiceFactory.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new AccountFindByIdServiceImpl(
                            DatabaseConnectionServiceFactory.getInstance()
                    );
                }

            }
        }

        return instance;
    }
}
