package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMysqlConnect {

    String url = "jdbc:mysql://localhost:3306/test";
    String user = "root";
    String password = "password";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công đến MySQL Database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        TestMysqlConnect app = new TestMysqlConnect();
        app.connect();
    }

}
