package org.example.didemo;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User findUserById(Long userId) {

        User user = new User();

        try (Connection conn = Application.INSTANCE.dataSource().getConnection()) {
            PreparedStatement statement = conn.prepareStatement("select * from users where user_id = ?");
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setId(rs.getLong("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
