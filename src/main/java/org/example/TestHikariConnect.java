package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class TestHikariConnect {

    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "password";

    public static void main(String[] args) throws SQLException {

        // Mở duy nhất một kết nối tới database thông qua Hikari Connection Pool
        Connection conn = createdHikariDS().getConnection();

        // thực hiện truy vấn SELECT thông qua kết nối cung cấp từ Hikari Connection Pool
        String selectQuery = "select * from users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectQuery);
        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            System.out.println("firstName = " + firstName + "," + "lastName= " + lastName);
        }

        // Tiếp tực truy vấn INSERT thông qua Hikari Connection Pool ở trên
        String insertInto = "INSERT INTO users (first_name, last_name) VALUES (?,?)";
        PreparedStatement insert = conn.prepareStatement(insertInto);
        insert.setString(1, "Bao Chau");
        insert.setString(2, "Ngo");
        int insertedRows = insert.executeUpdate();
        System.out.println("\nĐã thêm vào " + insertedRows + " hàng");
    }

    // Tạo DataSource thay thế cho DriverManager từ các thông tin đăng nhập ở trên
    static DataSource createdHikariDS() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
