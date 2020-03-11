package com.logrolling.server;

import com.logrolling.server.database.MySQLDatabase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.sql.*;

public class Main implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {
        // Perform action during application's startup
        MySQLDatabase.init();
    }

    public void contextDestroyed(ServletContextEvent e) {
        // Perform action during application's shutdown

        //TODO: Close database data and free memory
    }
}

