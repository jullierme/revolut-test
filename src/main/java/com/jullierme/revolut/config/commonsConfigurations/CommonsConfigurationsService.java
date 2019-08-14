package com.jullierme.revolut.config.commonsConfigurations;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class CommonsConfigurationsService {
    private static final String FILE_NAME = "application.properties";

    public static Configuration configuration;

    public static void load() {
        final Configurations configs = new Configurations();

        try {
            configuration = configs.properties(new File(FILE_NAME));
        } catch (ConfigurationException cex) {
            throw new RuntimeException("Error when load " + FILE_NAME + " file. Error: " + cex.toString());
        }
    }
}
