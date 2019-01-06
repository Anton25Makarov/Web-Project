package com.epam.library.repositpry;


import com.epam.library.builder.Builder;
import com.epam.library.specification.SqlSpecification;
import com.epam.library.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    public static final int FIRST_COLUMN = 1;
    protected Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    /*private void takeConnection() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void returnConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.returnConnection(connection);
    }*/

    @Override
    public List<T> query(SqlSpecification sqlSpecification) throws SQLException {
        // ret execQuery
        return null;
    }

    @Override
    public Optional<T> queryForSingleResult(SqlSpecification sqlSpecification) throws SQLException {
        return Optional.empty();
    }
    // get table name?

    @Override
    public abstract boolean save(T t) throws SQLException;

    @Override
    public abstract boolean remove(T t) throws SQLException;

    protected abstract Builder<T> getBuilder();

    protected Optional<T> executeQueryForSingleResult(Builder<T> builder, String query, List<String> parameters)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            T entity = null;
            if (resultSet.next()) {
                entity = builder.build(resultSet);

            }

            return Optional.ofNullable(entity);
        }
    }

    protected List<T> executeQuery(Builder<T> builder, String query, List<String> parameters) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = FIRST_COLUMN;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery(); //close?

            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }

            return entities;
        }
    }

}
