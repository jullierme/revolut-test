package com.jullierme.revolut.config.flyway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;

public class FlywayDatabaseMigrationService {
    private static final Logger logger = LogManager.getLogger(FlywayDatabaseMigrationService.class);

    public static void createDatabase() {
        logger.info("Creating the database...");

        String url = configuration.getString("datasource.url");
        String username = configuration.getString("datasource.username");

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure()
                .dataSource(url, username, null)
                .load();

        // Start the migration
        flyway.migrate();
    }
}
