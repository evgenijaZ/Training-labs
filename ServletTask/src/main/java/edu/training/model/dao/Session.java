package edu.training.model.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

class Session {
    private DataSource dataSource;
    private Connection connection;


    Session(DataSource dataSource) {
        this.dataSource = dataSource;
        connection = openConnection();
    }

    private Connection openConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement getStatement() {
        try {
            if (connection.isClosed())
                connection = openConnection();
            return Objects.requireNonNull(connection).createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            if (connection.isClosed()) connection = openConnection();
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


    void closeStatement(Statement statement) {
        try {
            Objects.requireNonNull(statement).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}