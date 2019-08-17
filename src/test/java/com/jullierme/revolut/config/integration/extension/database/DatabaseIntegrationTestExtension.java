package com.jullierme.revolut.config.integration.extension.database;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseIntegrationTestExtension implements BeforeAllCallback, BeforeEachCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        CommonsConfigurationsService.load();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        FlywayDatabaseMigrationService.createDatabase();
    }
}
