package com.jullierme.revolut.config.flyway;

import org.flywaydb.core.Flyway;

import static com.jullierme.revolut.config.commons.configurations.CommonsConfigurationsService.configuration;

public class FlywayDatabaseMigrationService {
    public static void createDatabase() {
        final String url = configuration.getString("datasource.url");
        final String username = configuration.getString("datasource.username");

        // Create the Flyway instance and point it to the database
        final Flyway flyway = Flyway.configure()
                .dataSource(url, username, null)
                .load();

        // Start the migration
        flyway.migrate();
    }
}
