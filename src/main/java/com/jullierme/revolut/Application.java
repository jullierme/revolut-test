package com.jullierme.revolut;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String DEFAULT_PATH = "/api/*";
    private static final String DEFAULT_PACKAGE = "com.jullierme.revolut";
    private static final String JERSEY_PACKAGES = "jersey.config.server.provider.packages";

    public static void main(String[] args) {
        startJetty();
    }

    private static void startJetty() {
        Server server = new Server(PORT);

        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath(CONTEXT_PATH);

        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(
                ServletContainer.class, DEFAULT_PATH);

        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(JERSEY_PACKAGES, DEFAULT_PACKAGE);

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            logger.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        } finally {
            server.destroy();
        }
    }
}
