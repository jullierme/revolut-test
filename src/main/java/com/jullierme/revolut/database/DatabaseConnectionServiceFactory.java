package com.jullierme.revolut.database;

public class DatabaseConnectionServiceFactory {
    private static DatabaseConnectionServiceFactory databaseConnectionServiceFactory;
    private static DatabaseConnectionService databaseConnectionService;

    public static DatabaseConnectionServiceFactory getInstance() {
        if (databaseConnectionServiceFactory == null) {
            databaseConnectionServiceFactory = new DatabaseConnectionServiceFactory();
        }

        return databaseConnectionServiceFactory;
    }

    public DatabaseConnectionService getDatabaseConnectionService() {
        if (databaseConnectionService == null) {
            databaseConnectionService = new DatabaseConnectionServiceImpl();
        }

        return databaseConnectionService;
    }
}
