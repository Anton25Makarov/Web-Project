package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.GenreBuilder;
import com.epam.library.model.BookGenre;
import com.epam.library.specification.SqlSpecification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreRepository extends AbstractRepository<BookGenre> {
    private static final String SHOW_COLUMNS_QUERY = "show columns from `genre_catalog`";
    private static final String SELECT_QUERY = "select * from genre_catalog ";
    private static final String INSERT_QUERY =
            "insert into genre_catalog (genre)\n" +
                    "values (?);";

    public GenreRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<BookGenre> query(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();
        Builder<BookGenre> builder = getBuilder();


        return executeQuery(builder, query, parameters);
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<BookGenre> queryForSingleResult(SqlSpecification specification) {
        throw new UnsupportedOperationException();
    }

    protected Builder<BookGenre> getBuilder() {
        return new GenreBuilder();
    }

    protected Optional<BookGenre> executeQueryForSingleResult(
            Builder<BookGenre> builder, String query, List<String> parameters) {
        throw new UnsupportedOperationException(); // add text error as parameter
    }

//    private List<BookGenre> executeQuery(Builder<BookGenre> builder, String query, List<String> parameters)
//            throws SQLException {
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            int i = 1;
//            for (String parameter : parameters) {
//                preparedStatement.setString(i++, parameter);
//            }
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            List<BookGenre> genres = new ArrayList<>();
//            while (resultSet.next()) {
//                BookGenre bookGenre = builder.build(resultSet);
//                genres.add(bookGenre);
//            }
//
//            return genres;
//        } catch (SQLException e) {
//            throw new SQLException(); //own exception
//        }
//    }

    @Override
    public boolean save(BookGenre genre) throws SQLException {

        boolean executingFlag;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, genre.getGenre());

            preparedStatement.executeUpdate();
            executingFlag = true;
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }

        return executingFlag;
    }

    @Override
    public boolean remove(BookGenre genre) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> queryColumnsNames() throws SQLException {
        return executeQueryColumnsNames(SHOW_COLUMNS_QUERY);    }
}
