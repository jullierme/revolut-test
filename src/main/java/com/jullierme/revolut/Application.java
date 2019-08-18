package com.jullierme.revolut;

import com.jullierme.revolut.config.commons.configurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.jetty.JettyService;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Application {
    public static void main(String[] args) throws ConfigurationException {
        CommonsConfigurationsService.load();

        FlywayDatabaseMigrationService.createDatabase();

        JettyService.start();
    }
}
