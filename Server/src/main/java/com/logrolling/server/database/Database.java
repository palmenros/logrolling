package com.logrolling.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    //Database constructor should create a connection to the database

    /**
     * Executes given query in the database
     * Used for queries that retrieve data
     *
     * @param queryString Database query such as SQL
     * @return result set given by database
     */
    ResultSet executeQuery(String queryString) throws DatabaseException;

    /**
     * Execute a parametrized query to avoid injections.
     * Query should have placeholders given by the character "?"
     * Query params will be replaced in given order by given placeholders
     *
     * @param query       Database query pattern with placeholders
     * @param queryParams Params to replace placeholders in order
     * @return result set given by database
     */
    ResultSet executeQuery(String query, String[] queryParams) throws DatabaseException;

    /**
     * Execute given query in the database
     * Used for queries that update or modify the database
     *
     * @param queryString Database query such as SQL
     * @return Number of rows affected by query
     * @throws DatabaseException
     */
    int executeUpdate(String queryString) throws DatabaseException;

    /**
     * Executes a parametrized query to avoid injections
     * Query should have placeholders given by the character "?"
     * Used for queries that update or modify the database
     *
     * @param query       Database query such as SQL with placeholders
     * @param queryParams Params to replace placeholders in order
     * @return Number of rows affected by query
     * @throws DatabaseException
     */
    int executeUpdate(String query, String[] queryParams) throws DatabaseException;

    /**
     * Delete all tables that are in the database
     */
    void deleteAllTables() throws DatabaseException;

    /**
     * Close connection to avoid resource leaking
     */
    void close() throws DatabaseException;
}
