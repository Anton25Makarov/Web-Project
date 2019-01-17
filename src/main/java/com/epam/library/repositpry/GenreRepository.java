package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.GenreBuilder;
import com.epam.library.model.BookGenre;
import com.epam.library.specification.SqlSpecification;

import java.sql.*;
import java.util.*;

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

        return executeQuery(query, parameters);
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

    @Override
    public void save(BookGenre genre) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();

        map.put(1, genre.getGenre());

        executeSave(map, INSERT_QUERY);
    }

    @Override
    public void remove(BookGenre genre) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
