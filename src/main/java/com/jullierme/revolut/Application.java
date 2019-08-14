package com.jullierme.revolut;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.jetty.JettyService;

public class Application {
    public static void main(String[] args) {
        CommonsConfigurationsService.load();

        FlywayDatabaseMigrationService.createDatabase();

        JettyService.start();
    }
}
