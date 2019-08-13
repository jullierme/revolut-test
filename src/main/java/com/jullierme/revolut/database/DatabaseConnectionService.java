package com.jullierme.revolut.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionService {
    Connection getConnection() throws SQLException;
}
