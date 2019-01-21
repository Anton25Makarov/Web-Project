package com.epam.library.repositpry;

import com.epam.library.builder.AuthorBuilder;
import com.epam.library.builder.Builder;
import com.epam.library.exception.RepositoryException;
import com.epam.library.model.Author;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuthorRepository extends AbstractRepository<Author> {
    private static final String SELECT_QUERY = "select * from book_author\n";
    private static final String INSERT_QUERY =
            "insert into book_author (name, surname)\n" +
                    "values (?, ?);";


    public AuthorRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Author> query(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query" + e);
        }
    }

    @Override
    public Optional<Author> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQueryForSingleResult(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query for single result" + e);
        }
    }

    @Override
    public void save(Author author) throws RepositoryException {
        try {
            Map<Integer, Object> map = new HashMap<>();
            int i = 1;

            map.put(i++, author.getName());
            map.put(i, author.getSurname());

            executeSave(map, INSERT_QUERY);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute save entity" + e);
        }
    }

    @Override
    public void remove(Author author) {
        throw new UnsupportedOperationException("Operation 'remove' is not defined");
    }

    protected Builder<Author> getBuilder() {
        return new AuthorBuilder();
    }
}
