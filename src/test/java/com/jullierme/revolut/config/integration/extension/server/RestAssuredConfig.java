package com.jullierme.revolut.config.integration.extension.server;

import io.restassured.RestAssured;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;

public class RestAssuredConfig {

    public static void config() {
        restAssuredPortConfig();
        restAssuredBasePathConfig();
        restAssuredBaseURIConfig();
    }

    private static void restAssuredBaseURIConfig() {
        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = configuration.getString("app.server.host");
        }
        RestAssured.baseURI = baseHost;
    }

    private static void restAssuredBasePathConfig() {
        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = configuration.getString("app.server.context_path");
        }
        RestAssured.basePath = basePath;
    }

    private static void restAssuredPortConfig() {
        String port = System.getProperty("server.port");

        if (port == null) {
            RestAssured.port = configuration.getInt("app.server.port");
        } else {
            RestAssured.port = Integer.valueOf(port);
        }
    }
}
