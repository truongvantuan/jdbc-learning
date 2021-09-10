package org.example.didemo;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
public class MyApplicationContextConfiguration {

    @Bean
    @Scope("singleton")
    public DataSource createDs() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean()
    UserDao userDao() {
        return new UserDao();
    }
}
