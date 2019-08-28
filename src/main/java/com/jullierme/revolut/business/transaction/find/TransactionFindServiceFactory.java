package com.jullierme.revolut.business.transaction.find;

import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

/*
 * Thread Safe Singleton
 *   - Lazy initialization with Double check locking
 * */
public class TransactionFindServiceFactory {
    private static volatile TransactionFindByIdService instance;

    private TransactionFindServiceFactory() {
    }

    public static TransactionFindByIdService getInstance() {
        /*Using localRef, we are reducing the access of volatile variable to just one for positive use case.*/
        TransactionFindByIdService localRef = instance;

        if (localRef == null) {
            //synchronized block to remove overhead
            synchronized (TransactionFindServiceFactory.class) {
                localRef = instance;

                if (localRef == null) {
                    // if instance is null, initialize
                    instance = localRef = new TransactionFindByIdServiceImpl(DatabaseConnectionServiceFactory.getInstance());
                }

            }
        }

        return localRef;
    }
}
