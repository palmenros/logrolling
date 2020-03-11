package com.logrolling.server;

import com.logrolling.server.database.MySQLDatabase;
import com.logrolling.server.database.migrations.MigrationManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Main implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {
        // Perform action during application's startup
        MySQLDatabase.init();

        // TODO: Remove when depolying in production

        // Migrate all databases and fill them with dummy data for development and testing
        MigrationManager.migrate();
        MigrationManager.fill();
    }

    public void contextDestroyed(ServletContextEvent e) {
        // Perform action during application's shutdown

        // TODO: Close database data and free memory
    }
}

