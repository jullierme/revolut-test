package com.jullierme.revolut;

import com.jullierme.revolut.config.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.JettyService;
import com.jullierme.revolut.config.PropertiesConfigurationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Jullierme, Revolut Needs You!");

        PropertiesConfigurationService.load();

        FlywayDatabaseMigrationService.createDatabase();

        JettyService.start();
    }
}
