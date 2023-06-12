package com.example.home;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    Connection conn;
    static String userName;
    static String password;
    static String ip;
    static String port;
    static String dbName;

    public static Connection connection() {
        ip       = "127.0.0.1";
        dbName   = "home";
        userName = "home";
        password = "KeshavSaraogi";
        port     = "3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionURL = null;

        try {
            Class.forName("net.source.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://"
                    + ip + ":" + port + ";" + "databasename=" + dbName
                    + ";user=" + userName + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return connection;
    }
}
