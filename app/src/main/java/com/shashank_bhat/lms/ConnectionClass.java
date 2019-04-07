package com.shashank_bhat.lms;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    static String username = "admin";
    static String password = "admin";
    static String driver = "net.sourceforge.jtds.jdbc.Driver";
    static String serverIP = "192.168.43.99";
    static String instance = "SQLEXPRESS";
    static String DatabaseName = "DBMS";

    public static Connection CON() {
        try {
            Connection con = null;
            Class.forName(driver).newInstance();
            String url = "jdbc:jtds:sqlserver://" + serverIP + ":1433/" + instance + ";DatabaseName=" + DatabaseName;
            con = DriverManager.getConnection(url, username, password);
            return con;

        } catch (Exception e) {
        }
        return null;
    }
}