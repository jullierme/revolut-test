package com.jullierme.revolut.config.integration.extension.server;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.flyway.FlywayDatabaseMigrationService;
import com.jullierme.revolut.config.jetty.JettyService;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ServerIntegrationTestExtension implements BeforeAllCallback, AfterAllCallback{
    private Server server;

    @Override
    public void beforeAll(ExtensionContext context) {
        CommonsConfigurationsService.load();

        FlywayDatabaseMigrationService.createDatabase();

        server = JettyService.start();

        RestAssuredConfig.config();
    }


    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if(server != null)
            server.stop();
    }
}
