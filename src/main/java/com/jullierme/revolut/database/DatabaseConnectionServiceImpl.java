package com.jullierme.revolut.database;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;

public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

    private static DataSource datasource;

    static {
        dataSourceConfig();
    }

    static void dataSourceConfig() {
        PoolProperties p = new PoolProperties();
        p.setUrl(configuration.getString("datasource.url"));
        p.setDriverClassName(configuration.getString("datasource.driverClassName"));
        p.setUsername(configuration.getString("datasource.username"));
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        datasource = new DataSource();
        datasource.setPoolProperties(p);
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn;

        Future<Connection> future = datasource.getConnectionAsync();

        while (!future.isDone()) {
            try {
                Thread.sleep(100); //simulate work
            } catch (InterruptedException x) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            conn = future.get();
            conn.setAutoCommit(false);

            return conn;
        } catch (InterruptedException | ExecutionException e) {
            throw new SQLException("Connection refused");
        }

    }
}
