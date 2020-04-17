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

        User[] userList = new User[] {
                //password: "password"
            new User("pedro", "c6318dd2fa58093bc6c24dcfebf32a31:fdcea1f7fc4ca2add08aac757d84caae5415a3b06f7e04367bb20a5042a4d824fc8eaebe1b59f4199dcf679006127d26e5d0950b94ad1cddbd802e4e3a887718", 50),
                //password: "1234"
            new User("pablo", "2c1237903ea6f46aef1dffba6d44a79a:39624de005c8ba398ea2c7beb1d2261822b089c221c0d4b2148382005b4b710d9bdb2908781a4983157d6402f9a066e41cb14e0ce8afd82aa18c61a7acaa3ecb", 30),
                //password: "defrutos"
            new User ("juancarlos", "a471390059ac80fdf576527b3b2a2caf:3b1d5339b18d426427c7bf73abd33e9917b8afcc723ee14eadcb9d2383fba4d7876392ccd8ea66ad81118a8d950abfc66c8674db767d00a95f9be3187e59c986", 0)
        };

        for(User user : userList) {
            UserManager.createUser(user);
        }
    }
}
