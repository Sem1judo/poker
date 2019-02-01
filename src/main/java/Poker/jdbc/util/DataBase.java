package Poker.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB = "jdbc:mysql://localhost:3307/onlinePoker?autoReconnect=true&useSSL=false";

    public static Connection getConnect() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(DB, USER, PASS);
            System.out.println("Success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


}
