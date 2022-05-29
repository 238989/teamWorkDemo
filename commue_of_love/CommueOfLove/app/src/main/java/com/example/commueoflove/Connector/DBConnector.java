package com.example.commueoflove.Connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private String driver = "";
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private static DBConnector connection = null;

    private DBConnector() throws Exception {
        driver = "com.mysql.jdbc.Driver";
        dbURL = "jdbc:mysql://rm-bp15a4a5qy09y2bnsdo.mysql.rds.aliyuncs.com:3306/commue_of_love";
        user = "root";
        password = "R00tR00t";
        System.out.println("dbURL:" + dbURL);
    }
    public static Connection getConnection() {
        Connection conn = null;
        if (connection == null) {
            try {
                connection = new DBConnector();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Class.forName(connection.driver);
            conn = DriverManager.getConnection(connection.dbURL,
                    connection.user, connection.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
