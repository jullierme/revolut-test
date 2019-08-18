package com.jullierme.revolut.config.commons.configurations;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class CommonsConfigurationsService {
    private static final String FILE_NAME = "application.properties";

    public static Configuration configuration;

    public static void load() throws ConfigurationException {
        final Configurations configs = new Configurations();

        configuration = configs.properties(new File(FILE_NAME));
    }
}
