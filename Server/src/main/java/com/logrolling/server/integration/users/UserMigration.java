package com.logrolling.server.integration.users;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.migrations.Migration;
import com.logrolling.server.integration.users.UserDAO;
import com.logrolling.server.services.users.User;

public class UserMigration implements Migration {

    /**
     * Creates tables necessary for model manager of given class
     */
    @Override
    public void migrate() {

        //Create User Table
        String sqlQuery =
                "create table if not exists users (" +
                        "id int auto_increment," +
                        "username varchar(50) not null," +
                        "password varchar(165) not null," +
                        "grollies int," +
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

        User[] userList = new User[]{
                new User("pedro", "password", 50000),
                new User("pablo", "1234", 30),
                new User("juancarlos", "defrutos", 0)
        };

        for (User user : userList) {
            UserDAO.createUser(user);
        }
    }
}
