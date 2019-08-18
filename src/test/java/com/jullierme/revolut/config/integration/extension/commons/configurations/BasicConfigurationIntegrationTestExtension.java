package com.jullierme.revolut.config.integration.extension.commons.configurations;

import com.jullierme.revolut.config.commons.configurations.CommonsConfigurationsService;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BasicConfigurationIntegrationTestExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws ConfigurationException {
        CommonsConfigurationsService.load();
    }
}
