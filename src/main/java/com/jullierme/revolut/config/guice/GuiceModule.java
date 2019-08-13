package com.jullierme.revolut.config.guice;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind(TransactionLog.class).to(DatabaseTransactionLog.class);
    }

}
