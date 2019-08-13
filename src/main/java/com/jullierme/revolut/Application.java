package com.jullierme.revolut;

import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.guice.GuiceService;
import com.jullierme.revolut.config.jetty.JettyService;
import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Jullierme, Revolut Needs You!");

        CommonsConfigurationsService.load();

        FlywayDatabaseMigrationService.createDatabase();

        GuiceService.creactInjectors();

        JettyService.start();
    }
}
