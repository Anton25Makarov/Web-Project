package com.epam.library.repositpry;

import com.epam.library.builder.AuthorBuilder;
import com.epam.library.builder.Builder;
import com.epam.library.model.Author;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepository extends AbstractRepository<Author> {
    private static final String SELECT_QUERY = "select * from book_author ";
    private static final String INSERT_QUERY =
            "insert into book_author (name, surname) \n" +
                    "values (?, ?);";

    public AuthorRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Author> query(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();
        Builder<Author> builder = getBuilder();

        return executeQuery(builder, query, parameters);
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<Author> queryForSingleResult(SqlSpecification specification) {
        throw new UnsupportedOperationException();
    }

    protected Builder<Author> getBuilder() {
        return new AuthorBuilder();
    }

    protected Optional<Author> executeQueryForSingleResult(
            Builder<Author> builder, String query, List<String> parameters) {
        throw new UnsupportedOperationException(); // add text error as parameter
    }

    @Override
    public boolean save(Author author) throws SQLException {

        boolean executingFlag;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());

            preparedStatement.executeUpdate();
            executingFlag = true;
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }

        return executingFlag;
    }

    @Override
    public boolean remove(Author author) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
