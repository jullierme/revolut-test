package com.jullierme.revolut.config.integration.extension.server;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.config.JsonPathConfig;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;
import static io.restassured.config.JsonConfig.jsonConfig;

public class RestAssuredConfig {

    public static void config() {
        restAssuredPortConfig();
        restAssuredBasePathConfig();
        restAssuredBaseURIConfig();

        configDefaultParser();

        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    private static void configDefaultParser() {
        RestAssured.defaultParser = Parser.JSON;
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
