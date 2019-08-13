package com.jullierme.revolut.config.guice;

import com.google.inject.AbstractModule;
import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.database.DatabaseConnectionServiceImpl;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DatabaseConnectionService.class).to(DatabaseConnectionServiceImpl.class);
    }

}
