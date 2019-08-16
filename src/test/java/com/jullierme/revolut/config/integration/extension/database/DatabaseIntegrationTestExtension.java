package com.jullierme.revolut.config.integration.extension.database;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseIntegrationTestExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        CommonsConfigurationsService.load();

        FlywayDatabaseMigrationService.createDatabase();
    }
}
