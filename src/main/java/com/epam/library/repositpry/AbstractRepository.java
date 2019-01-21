package com.epam.library.repositpry;


import com.epam.library.builder.Builder;
import com.epam.library.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    protected Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    protected void executeRemove(T t, String removeQuery) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {
            preparedStatement.setLong(1, t.getId());

            preparedStatement.executeUpdate();
        }
    }

    protected void executeSave(Map<Integer, Object> map, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Map.Entry<Integer, Object> entry : map.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                preparedStatement.setObject(key, value);
            }

            preparedStatement.executeUpdate();
        }
    }

    protected List<T> executeQuery(String query, List<String> parameters) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery(); //close?

            Builder<T> builder = getBuilder();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }

            return entities;
        }
    }

    protected Optional<T> executeQueryForSingleResult(String query, List<String> parameters) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            T entity = null;
            Builder<T> builder = getBuilder();
            if (resultSet.next()) {
                entity = builder.build(resultSet);
            }

            return Optional.ofNullable(entity);
        }
    }

    protected abstract Builder<T> getBuilder();
}
