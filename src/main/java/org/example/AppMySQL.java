package org.example;

import java.sql.*;

public class AppMySQL {

    // Thông tin tài khoản MySQL khi khởi tạo từ docker-compose.yml
    private String url = "jdbc:mysql://localhost:3306/test";
    private String user = "root";
    private String password = "password";

    public static void main(String[] args) {
        AppMySQL app = new AppMySQL();
        app.getUserWithName("tuan");
        app.insertIntoTable("khanh", "nguyen");
        app.updateUser(4);
    }

    // Tạo kết nối tới database thông qua DriverManager
    Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //
    void getUserWithName(String name) {
        String sql = "SELECT * FROM users WHERE first_name = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement selectStatement = conn.prepareStatement(sql);
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                System.out.println("firstName = " + firstName + "," + "lastName= " + lastName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void insertIntoTable(String first_name, String last_name) {
        String sql = "INSERT INTO users (first_name, last_name) VALUES (?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            int insertedRows = statement.executeUpdate();
            System.out.println("Đã chèn vào bảng " + insertedRows + " user");
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
    }

    void updateUser(int user_id) {
        String sql = "UPDATE users SET first_name = \"unknown\" WHERE user_id = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setInt(1, user_id);
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("Đã cập nhật " + updatedRows + " user");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
