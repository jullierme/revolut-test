package com.jullierme.revolut.database;

public class DatabaseConnectionServiceFactory {
    private static DatabaseConnectionService instance;

    public static DatabaseConnectionService getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionServiceImpl();
        }

        return instance;
    }
}
