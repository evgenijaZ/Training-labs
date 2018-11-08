package edu.training.controllers.servlets;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class InitContext {
    private static MysqlDataSource dataSource;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        dataSource = new MysqlDataSource();
        dataSource.setURL(bundle.getString("url"));
        dataSource.setUser(bundle.getString("user"));
        dataSource.setPassword(bundle.getString("password"));
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
