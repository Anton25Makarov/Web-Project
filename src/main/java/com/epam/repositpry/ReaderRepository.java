package com.epam.repositpry;

import com.epam.builder.Builder;
import com.epam.builder.ReaderBuilder;
import com.epam.model.Employee;
import com.epam.model.Entity;
import com.epam.model.Reader;
import com.epam.specification.SqlSpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderRepository extends AbstractRepository {
    private static final String SELECT_QUERY = "select * from reader ";


    public ReaderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Entity> query(SqlSpecification readerSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Reader> queryForSingleResult(SqlSpecification specification)
            throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Reader> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }


    @Override
    protected Builder<Reader> getBuilder() {
        return new ReaderBuilder();
    }

    private Optional<Reader> executeQueryForSingleResult(
            Builder<Reader> builder, String query, List<String> parameters) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            Reader reader = null;
            if (resultSet.next()) {
                reader = builder.build(resultSet);

            }

            return Optional.ofNullable(reader);
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }
    }
}
