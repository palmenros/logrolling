package com.logrolling;

class DatabaseSetting {

    String key;
    String value;

    public DatabaseSetting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}

public class Settings {

    private static String databaseDriver = "com.mysql.cj.jdbc.Driver";
    private static String databaseUser = "root";
    private static String databasePassword = "";

    private static int databasePort = 3306;
    private static String databaseName = "logrolling";
    private static String databaseURL = "localhost";
    private static String databaseProvider = "jdbc:mysql";

    private static DatabaseSetting[] databaseSettings = new DatabaseSetting[]{
            new DatabaseSetting("useUnicode", "true"),
            new DatabaseSetting("useJDBCCompliantTimezoneShift", "true"),
            new DatabaseSetting("useLegacyDatetimeCode", "false"),
            new DatabaseSetting("serverTimezone", "UTC")
    };

    /**
     * Getters
     **/

    public static String getDatabaseDriver() {
        return databaseDriver;
    }

    public static String getDatabaseConnectionURL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(databaseProvider).append("://").append(databaseURL).append(":").append(databasePort);
        stringBuilder.append("/").append(databaseName);

        //Encode database settings
        int settingsLength = databaseSettings.length;
        if (settingsLength > 0) {
            stringBuilder.append('?');

            for (int i = 0; i < settingsLength; i++) {
                stringBuilder.append(databaseSettings[i].getKey());
                stringBuilder.append("=");
                stringBuilder.append(databaseSettings[i].getValue());

                //Append & if not last setting
                if (i != settingsLength - 1) {
                    stringBuilder.append("&");
                }
            }
        }

        return stringBuilder.toString();
    }

    public static String getDatabaseUser() {
        return databaseUser;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }

    public static String getDatabaseName() {
        return databaseName;
    }
}
