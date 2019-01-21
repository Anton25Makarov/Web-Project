package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.GenreBuilder;
import com.epam.library.exception.RepositoryException;
import com.epam.library.model.BookGenre;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenreRepository extends AbstractRepository<BookGenre> {
    private static final String SELECT_QUERY = "select * from genre_catalog\n";
    private static final String INSERT_QUERY =
            "insert into genre_catalog (genre)\n" +
                    "values (?);";

    public GenreRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<BookGenre> query(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query" + e);
        }
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<BookGenre> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQueryForSingleResult(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query for single result" + e);
        }
    }

    protected Builder<BookGenre> getBuilder() {
        return new GenreBuilder();
    }


    @Override
    public void save(BookGenre genre) throws RepositoryException {
        try {
            Map<Integer, Object> map = new HashMap<>();

            map.put(1, genre.getGenre());

            executeSave(map, INSERT_QUERY);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute save entity" + e);
        }
    }

    @Override
    public void remove(BookGenre genre) {
        throw new UnsupportedOperationException("Operation 'remove' is not defined");
    }

}
