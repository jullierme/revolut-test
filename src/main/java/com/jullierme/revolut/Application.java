package com.jullierme.revolut;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.jetty.JettyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting Application");

        CommonsConfigurationsService.load();

        FlywayDatabaseMigrationService.createDatabase();

        JettyService.start();
    }
}
