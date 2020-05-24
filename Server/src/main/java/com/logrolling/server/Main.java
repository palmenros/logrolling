package com.logrolling.server;

import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.factories.MySQLDatabaseFactory;
import com.logrolling.server.database.migrations.MigrationManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Main implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {
        // Perform action during application's startup

        //Use MySQL database by default
        DatabaseFactory.setFactory(new MySQLDatabaseFactory());
        // Migrate all databases and fill them with dummy data for development and testing
        try {

            String systemProperty = System.getProperty("LOGROLLING_RECREATE_TABLES");
            boolean recreateTables = "TRUE".equals(systemProperty);

            MigrationManager.migrate(recreateTables);

            if(recreateTables) {
                MigrationManager.fillDummy();
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent e) {
        // Perform action during application's shutdown
    }
}

