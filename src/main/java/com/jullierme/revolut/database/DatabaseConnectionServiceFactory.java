package com.jullierme.revolut.database;

public class DatabaseConnectionServiceFactory {
    private static volatile DatabaseConnectionService instance;

    public static DatabaseConnectionService getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (DatabaseConnectionServiceFactory.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new DatabaseConnectionServiceImpl();
                }

            }
        }

        return instance;
    }
}
