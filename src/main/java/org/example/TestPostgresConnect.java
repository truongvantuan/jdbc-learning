package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kết nối PostgreSQL vào JDBC
 */
public class TestPostgresConnect {

    private final String url = "jdbc:postgresql://localhost:5432/dvdrental";
    private final String user = "admin";
    private final String password = "h68H6auu";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connect to PostgreSQL server successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        TestPostgresConnect app = new TestPostgresConnect();
        app.connect();
    }
}
