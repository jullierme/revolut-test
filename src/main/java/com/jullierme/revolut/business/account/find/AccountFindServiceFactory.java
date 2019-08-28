package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

/*
 * Thread Safe Singleton
 *   - Lazy initialization with Double check locking
 * */
public class AccountFindServiceFactory {
    private static volatile AccountFindByIdService instance;

    private AccountFindServiceFactory() {
    }

    public static AccountFindByIdService getInstance() {
        /*Using localRef, we are reducing the access of volatile variable to just one for positive usecase.*/
        AccountFindByIdService localRef = instance;

        if (localRef == null) {
            //synchronized block to remove overhead
            synchronized (AccountFindServiceFactory.class) {
                localRef = instance;

                if (localRef == null) {
                    // if instance is null, initialize
                    instance = localRef = new AccountFindByIdServiceImpl(
                            DatabaseConnectionServiceFactory.getInstance()
                    );
                }

            }
        }

        return localRef;
    }
}
