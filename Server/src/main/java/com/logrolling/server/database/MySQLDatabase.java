package com.logrolling.server.database;

import com.logrolling.Settings;
import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;

import java.sql.*;

public class MySQLDatabase implements Database {

    private Connection connection;


    public MySQLDatabase() throws DatabaseException {

        try {
            Class.forName(Settings.getDatabaseDriver());
            connection = DriverManager.getConnection(
                    Settings.getDatabaseConnectionURL(),
                    Settings.getDatabaseUser(),
                    Settings.getDatabasePassword()
            );
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet executeQuery(String queryString) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(queryString);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet executeQuery(String query, String[] queryParams) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < queryParams.length; i++) {
                statement.setString(i + 1, queryParams[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int executeUpdate(String queryString) throws DatabaseException {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(queryString);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int executeUpdate(String query, String[] queryParams) throws DatabaseException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < queryParams.length; i++) {
                statement.setString(i + 1, queryParams[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteAllTables() {
        //Get all existing tables
        ResultSet rs = executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = ?;",
                new String[]{
                        Settings.getDatabaseName()
                });

        try {
            //Generate a query to drop all those tables
            StringBuilder dropQuery = new StringBuilder("DROP TABLE ");

            int count = 0;
            while (rs.next()) {
                if (count != 0) {
                    dropQuery.append(',');
                }
                dropQuery.append(rs.getString("table_name"));
                count++;
            }

            if (count != 0) {
                dropQuery.append(";");
                executeUpdate(dropQuery.toString());
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

}
