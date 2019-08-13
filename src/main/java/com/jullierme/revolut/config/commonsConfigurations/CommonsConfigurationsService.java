package com.jullierme.revolut.config.commonsConfigurations;

import com.jullierme.revolut.Application;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class CommonsConfigurationsService {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static Configuration configuration;

    public static void load() {
        logger.info("Loading the application properties file");

        final Configurations configs = new Configurations();

        try {
            configuration = configs.properties(new File("application.properties"));
        } catch (ConfigurationException cex) {
            throw new RuntimeException("Error when load application.properties file. Error: " + cex.toString());
        }
    }
}
