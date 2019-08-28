package com.jullierme.revolut.database;

/*
 * Thread Safe Singleton
 *   - Lazy initialization with Double check locking
 * */
public class DatabaseConnectionServiceFactory {
    private static volatile DatabaseConnectionService instance;

    public static DatabaseConnectionService getInstance() {
        /*Using localRef, we are reducing the access of volatile variable to just one for positive use case.*/
        DatabaseConnectionService localRef = instance;

        if (localRef == null) {
            //synchronized block to remove overhead
            synchronized (DatabaseConnectionServiceFactory.class) {
                localRef = instance;

                if (localRef == null) {
                    // if instance is null, initialize
                    instance = localRef = new DatabaseConnectionServiceImpl();
                }

            }
        }

        return localRef;
    }
}
