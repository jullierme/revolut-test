package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

/*
 * Thread Safe Singleton
 *   - Lazy initialization with Double check locking
 * */
public class TransactionCreateServiceFactory {
    private static volatile TransactionCreateService instance;

    private TransactionCreateServiceFactory(){}

    public static TransactionCreateService getInstance() {
        /*Using localRef, we are reducing the access of volatile variable to just one for positive use case.*/
        TransactionCreateService localRef = instance;

        if (localRef == null) {
            //synchronized block to remove overhead
            synchronized (TransactionCreateServiceFactory.class) {
                localRef = instance;

                if (localRef == null) {
                    // if instance is null, initialize
                    instance = localRef = new TransactionCreateServiceImpl(
                            DatabaseConnectionServiceFactory.getInstance(),
                            TransactionFindServiceFactory.getInstance(),
                            AccountFindServiceFactory.getInstance()
                    );
                }

            }
        }

        return localRef;
    }
}
