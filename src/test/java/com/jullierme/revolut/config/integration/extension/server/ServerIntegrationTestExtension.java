package com.jullierme.revolut.config.integration.extension.server;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.guice.GuiceService;
import com.jullierme.revolut.config.jetty.JettyService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ServerIntegrationTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        CommonsConfigurationsService.load();

        GuiceService.creactInjectors();

        FlywayDatabaseMigrationService.createDatabase();

        JettyService.start();
    }
}
