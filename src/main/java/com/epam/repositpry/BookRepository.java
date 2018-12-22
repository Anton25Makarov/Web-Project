package com.epam.repositpry;

import com.epam.builder.BookBuilder;
import com.epam.builder.Builder;
import com.epam.model.Book;
import com.epam.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository extends AbstractRepository<Book> {
    private static final String SELECT_QUERY = "select * from book ";

    public BookRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Book> query(SqlSpecification specification) throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();
        Builder<Book> builder = getBuilder();


        return executeQuery(builder, query, parameters);
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<Book> queryForSingleResult(SqlSpecification specification) {
        throw new UnsupportedOperationException();
    }

    protected Builder<Book> getBuilder() {
        return new BookBuilder();
    }

    private Optional<Book> executeQueryForSingleResult(
            Builder<Book> builder, String query, List<String> parameters) {
        throw new UnsupportedOperationException(); // add text error as parameter
    }

    private List<Book> executeQuery(Builder<Book> builder, String query, List<String> parameters)
            throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = builder.build(resultSet);
                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }
    }
}
