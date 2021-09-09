package org.example;

import java.sql.*;

public class AppPostgreSQL {
    private final String url = "jdbc:postgresql://localhost:5432/dvdrental";
    private final String user = "admin";
    private final String password = "h68H6auu";

    public static void main(String[] args) {
        AppPostgreSQL app = new AppPostgreSQL();
        System.out.println(app.properCase("this is the actor list:"));
        System.out.println(app.getActorCount());
        System.out.println("Actor list: \n");
        app.getActors();
        System.out.println("\nFind actor by ID:");
        app.findActor(5);
    }

    /**
     * Kết nối đến PostgreSQL thông qua url, user, password
     *
     * @return Connection
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public String properCase(String s) {
        String result = s;
        try {
            Connection conn = this.connect();
            CallableStatement properCase = conn.prepareCall("{? = call initcap( ? ) }");
            properCase.registerOutParameter(1, Types.VARCHAR);
            properCase.setString(2, s);
            properCase.execute();
            result = properCase.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int getActorCount() {
        String sql = "SELECT COUNT(*) FROM actor";
        int count = 0;

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;
    }

    public void getActors() {
        String sql = "SELECT actor_id, first_name, last_name FROM actor";
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            displayActor(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayActor(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("actor_id") + "\t" +
                    resultSet.getString("first_name") + "\t" +
                    resultSet.getString("last_name"));
        }
    }

    public void findActor(int value) {
        String sql = "SELECT actor_id, first_name, last_name "
                + "FROM actor "
                + "WHERE LENGTH(first_name) < ?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, value);
            ResultSet rs = pstmt.executeQuery();
            displayActor(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
