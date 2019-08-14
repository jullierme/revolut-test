package com.jullierme.revolut.config.integration.extension.database;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.guice.GuiceService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseIntegrationTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        //System.setProperty("log4j.configurationFile", "log4j2-test.xml");

        CommonsConfigurationsService.load();

        GuiceService.creactInjectors();

        FlywayDatabaseMigrationService.createDatabase();
    }
}
