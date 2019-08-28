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
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (TransactionFindServiceFactory.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new TransactionFindByIdServiceImpl(DatabaseConnectionServiceFactory.getInstance());
                }

            }
        }

        return instance;
    }
}
