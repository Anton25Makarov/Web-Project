package com.epam.library.repositpry;

import com.epam.library.builder.BookBuilder;
import com.epam.library.builder.Builder;
import com.epam.library.exception.RepositoryException;
import com.epam.library.model.Book;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRepository extends AbstractRepository<Book> {
    private static final String SELECT_QUERY = "select * from book\n";
    private static final String REMOVE_QUERY = "delete from book where id = ?";
    private static final String INSERT_QUERY =
            "insert into book (title, year, count, book_author_id, genre_catalog_id)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY =
            "update book\n" +
                    "set title            = ?,\n" +
                    "    year             = ?,\n" +
                    "    count            = ?,\n" +
                    "    book_author_id   = ?,\n" +
                    "    genre_catalog_id = ?\n" +
                    "where id = ?;";

    public BookRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Book> query(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query" + e);
        }
    }

    @Override
    public Optional<Book> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQueryForSingleResult(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query for single result" + e);
        }
    }

    @Override
    public void save(Book book) throws RepositoryException {
        Map<Integer, Object> map = new HashMap<>();
        int i = 1;

        map.put(i++, book.getTitle());
        map.put(i++, book.getYear());
        map.put(i++, book.getCount());
        map.put(i++, book.getAuthor().getId());
        map.put(i++, book.getGenre().getId());

        try {
            if (book.getId() == null) {
                executeSave(map, INSERT_QUERY);
            } else {
                map.put(i, book.getId());
                executeSave(map, UPDATE_QUERY);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute save entity" + e);
        }
    }

    @Override
    public void remove(Book book) throws RepositoryException {
        try {
            executeRemove(book, REMOVE_QUERY);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute remove entity" + e);
        }
    }

    protected Builder<Book> getBuilder() {
        return new BookBuilder();
    }
}
