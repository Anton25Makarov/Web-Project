package com.epam.library.repositpry;

import com.epam.library.builder.BookBuilder;
import com.epam.library.builder.Builder;
import com.epam.library.model.Author;
import com.epam.library.model.BookGenre;
import com.epam.library.specification.SqlSpecification;
import com.epam.library.model.Book;

import java.sql.*;
import java.util.*;

public class BookRepository extends AbstractRepository<Book> {
    private static final String SELECT_QUERY = "select * from book ";
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
    public List<Book> query(SqlSpecification specification) throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        return executeQuery(query, parameters);
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<Book> queryForSingleResult(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Book> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    protected Builder<Book> getBuilder() {
        return new BookBuilder();
    }

   /* private Map<Integer, Object> getMapToAdd(Book book) {

        int i = 1;
        Map<Integer, Object> map = new HashMap<>();

        map.put(i++, book.getTitle());
        map.put(i++, book.getYear());
        map.put(i++, book.getCount());
        map.put(i++, book.getAuthor().getId());
        map.put(i, book.getGenre().getId());

        return map;
    }*/


    @Override
    public void save(Book book) throws SQLException {

        Map<Integer, Object> map = new HashMap<>();
        int i = 1;

        map.put(i++, book.getTitle());
        map.put(i++, book.getYear());
        map.put(i++, book.getCount());
        map.put(i++, book.getAuthor().getId());
        map.put(i++, book.getGenre().getId());

        if (book.getId() == null) {
            executeSave(map, INSERT_QUERY);
        } else {
            map.put(i, book.getId());
            executeSave(map, UPDATE_QUERY);
        }
    }

    @Override
    public void remove(Book book) throws SQLException {
        executeRemove(book, REMOVE_QUERY);
    }

}
