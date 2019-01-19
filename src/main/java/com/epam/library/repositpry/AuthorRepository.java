package com.epam.library.repositpry;

import com.epam.library.builder.AuthorBuilder;
import com.epam.library.builder.Builder;
import com.epam.library.exception.RepositoryException;
import com.epam.library.model.Author;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class AuthorRepository extends AbstractRepository<Author> {
    private static final String SELECT_QUERY = "select * from book_author ";
    private static final String INSERT_QUERY =
            "insert into book_author (name, surname) \n" +
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
            throw new RepositoryException(e);
        }
    }

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
    public void save(Author author) throws RepositoryException {
        try {
            Map<Integer, Object> map = new HashMap<>();
            int i = 1;

            map.put(i++, author.getName());
            map.put(i, author.getSurname());

            executeSave(map, INSERT_QUERY);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(Author author) {
        throw new UnsupportedOperationException();
    }

}
