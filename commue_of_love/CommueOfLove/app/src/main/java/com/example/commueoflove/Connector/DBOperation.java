package com.example.commueoflove.Connector;

public class DBOperation {
    private DBConnector conn;

    public DBOperation(){
        conn = (DBConnector) DBConnector.getConnection();
    }

    public void addList(){

    }
}
