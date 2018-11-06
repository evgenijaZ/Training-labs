package edu.training.controllers.servlets;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class InitContext {
    private static MysqlDataSource dataSource;

    static {
        dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/library");
        dataSource.setUser("root");
        dataSource.setPassword("root");
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
