package com.jullierme.revolut.config.integration.extension.database;

import com.jullierme.revolut.config.commons.configurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseIntegrationTestExtension implements BeforeAllCallback, BeforeEachCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws ConfigurationException {
        CommonsConfigurationsService.load();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        FlywayDatabaseMigrationService.createDatabase();
    }
}
