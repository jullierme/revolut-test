package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

/*
 * Thread Safe Singleton
 *   - Lazy initialization with Double check locking
 * */
public class AccountCreateServiceFactory {
    private static volatile AccountCreateService instance;

    private AccountCreateServiceFactory() {
    }

    public static AccountCreateService getInstance() {
        /*Using localRef, we are reducing the access of volatile variable to just one for positive use case.*/
        AccountCreateService localRef = instance;

        if (localRef == null) {
            //synchronized block to remove overhead
            synchronized (AccountCreateServiceFactory.class) {
                localRef = instance;

                if (localRef == null) {
                    // if instance is null, initialize
                    instance = localRef = new AccountCreateServiceImpl(
                            DatabaseConnectionServiceFactory.getInstance(),
                            AccountFindServiceFactory.getInstance()
                    );
                }

            }
        }

        return localRef;
    }
}
