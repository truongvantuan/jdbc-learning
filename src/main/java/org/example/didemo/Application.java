package org.example.didemo;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public enum Application {

    INSTANCE;

    private DataSource dataSource;

    public DataSource dataSource() {
        if (dataSource == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("password");
            dataSource.setURL("jdbc:mysql://localhost:3306/test");
            this.dataSource = dataSource;
        }
        return dataSource;
    }
}