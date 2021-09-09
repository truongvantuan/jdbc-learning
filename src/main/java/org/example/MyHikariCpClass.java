package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class MyHikariCpClass {

    private String url = "jdbc:mysql://localhost:3306/test";
    private String user = "root";
    private String password = "password";

    public DataSource createdDS() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
