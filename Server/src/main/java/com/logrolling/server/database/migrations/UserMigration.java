package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.User;

public class UserMigration implements Migration {

    /**
     * Creates tables necessary for model manager of given class
     */
    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table users (" +
                    "id int auto_increment,"+
                    "username varchar(50) not null," +
                    "password varchar(150) not null," +
                    "constraint users_pk primary key (id)" +
                ");";

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(sqlQuery);
    }



    /**
     * Fills tables affected by this migration with dummy data
     */
    @Override
    public void fillDummy() {

        User[] userList = new User[] {
            new User("pedro", "password"),
            new User("pablo", "1234"),
            new User ("juancarlos", "defrutos")
        };

        for(User user : userList) {
            UserManager.createUser(user);
        }
    }
}
