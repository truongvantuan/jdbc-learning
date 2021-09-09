package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnect {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            boolean isValid = conn.isValid(0);
            System.out.println("Trạng thái kết nối: " + isValid);
        }
    }
}
