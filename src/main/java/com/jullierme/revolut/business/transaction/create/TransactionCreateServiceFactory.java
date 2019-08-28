package com.jullierme.revolut.business.transaction.create;

import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.database.DatabaseConnectionServiceFactory;

public class TransactionCreateServiceFactory {
    private static volatile TransactionCreateService instance;

    private TransactionCreateServiceFactory(){}

    public static TransactionCreateService getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (TransactionCreateServiceFactory.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new TransactionCreateServiceImpl(
                            DatabaseConnectionServiceFactory.getInstance(),
                            TransactionFindServiceFactory.getInstance(),
                            AccountFindServiceFactory.getInstance()
                    );
                }

            }
        }

        return instance;
    }
}
