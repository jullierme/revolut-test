package com.jullierme.revolut.config.integration.extension.commonsConfigurations;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.config.guice.GuiceService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BasicConfigurationIntegrationTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        CommonsConfigurationsService.load();

        GuiceService.creactInjectors();
    }
}
