package com.jullierme.revolut.config.jetty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService.configuration;
import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class JettyService {
    private static final Logger logger = LogManager.getLogger(JettyService.class);

    public static void start() {
        logger.info("Starting Jetty Server...");

        final int port = configuration.getInt("app.server.port");
        final String contextPath = configuration.getString("app.server.context_path");
        final String prefixPath = configuration.getString("app.server.prefix_path");
        final String jettyPackages = configuration.getString("app.server.jetty_packages");
        final String rootPath = configuration.getString("app.server.root_path");

        final Server server = new Server(port);

        final ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath(contextPath);

        server.setHandler(servletContextHandler);

        final ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, prefixPath);
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(jettyPackages, rootPath);

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
