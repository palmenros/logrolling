package com.logrolling.server.database;

public class MySQLDatabase extends Database {

    // Private constructor: Singleton pattern
    private MySQLDatabase( /* args */ ) {

        //Possible MySQLDatabase creation:

        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/logrolling","root", "");
    }

    public static void init(/* args */) {
        Database.setInstance(new MySQLDatabase(/* args */));
    }

    //TODO: Complete class

    /* Non static methods for database use */
}
