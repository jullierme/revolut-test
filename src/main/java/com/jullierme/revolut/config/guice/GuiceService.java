package com.jullierme.revolut.config.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiceService {
    private static final Logger logger = LogManager.getLogger(GuiceService.class);

    public static Injector injector;

    public static void creactInjectors() {
        logger.info("Creating injectors...");

        injector = Guice.createInjector(new GuiceModule());
    }
}
