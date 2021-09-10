package org.example.didemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        UserDao userDao = ctx.getBean(UserDao.class);
        User user = userDao.findUserById(1L);
        DataSource ds = ctx.getBean(DataSource.class);
    }
}
