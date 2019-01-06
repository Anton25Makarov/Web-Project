package com.epam.library.repositpry;

import com.epam.library.builder.BookBuilder;
import com.epam.library.builder.Builder;
import com.epam.library.model.Author;
import com.epam.library.model.BookGenre;
import com.epam.library.specification.SqlSpecification;
import com.epam.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository extends AbstractRepository<Book> {
    private static final String SELECT_QUERY = "select * from book ";
    private static final String INSERT_QUERY =
            "insert into book (title, year, count, book_author_id, genre_catalog_id)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String REMOVE_QUERY = "delete from book where id = ?";
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

    protected Optional<Book> executeQueryForSingleResult(
            Builder<Book> builder, String query, List<String> parameters) {
        throw new UnsupportedOperationException(); // add text error as parameter
    }

//   /* private List<Book> executeQuery(Builder<Book> builder, String query, List<String> parameters)
//            throws SQLException {
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            int i = 1;
//            for (String parameter : parameters) {
//                preparedStatement.setString(i++, parameter);
//            }
//
//            List<Book> books = new ArrayList<>();
//            try(ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Book book = builder.build(resultSet);
//                    books.add(book);
//                }
//            }
////            ResultSet resultSet = preparedStatement.executeQuery();
//
//
//            return books;
//        } catch (SQLException e) {
//            throw new SQLException(); //own exception
//        }
//    }*/

    @Override
    public boolean save(Book book) throws SQLException {

        String title = book.getTitle();
        int year = book.getYear();
        int count = book.getCount();
        Author author = book.getAuthor();
        BookGenre genre = book.getGenre();

        if (book.getId() == null) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

                preparedStatement.setString(1, title);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, count);
                preparedStatement.setLong(4, author.getId());
                preparedStatement.setLong(5, genre.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }

            return true;
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

                preparedStatement.setString(1, title);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, count);
                preparedStatement.setLong(4, author.getId());
                preparedStatement.setLong(5, genre.getId());
                preparedStatement.setLong(6, book.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        }
        return true;
    }

    @Override
    public boolean remove(Book book) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {

            preparedStatement.setLong(1, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }

        return true;
    }

}

///* private int getBookAuthorId(Author author) throws SQLException {
//        int authorId = 0;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "select id from book_author where name = ? and surname  = ?")) {
//
//            preparedStatement.setString(1, author.getName());
//            preparedStatement.setString(2, author.getSurname());
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                authorId = resultSet.getInt("id");
//            }
//        }
//        return authorId;
//    }
//
//    private int getGenreId(String genre) throws SQLException {
//        int genreId = 0;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "select id from genre_catalog where genre = ?")) {
//
//            preparedStatement.setString(1, genre);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                genreId = resultSet.getInt("id");
//            }
//        }
//        return genreId;
//    }
//
//    private int saveAuthor(Author author) throws SQLException {
//        int authorId = 0;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "insert into book_author (name, surname)\n" +
//                        "values (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
//
//            preparedStatement.setString(1, author.getName());
//            preparedStatement.setString(2, author.getSurname());
//
//            preparedStatement.executeUpdate();
//
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//
//            if (resultSet.next()) {
//                authorId = resultSet.getInt(1);
//            }
//        }
//        return authorId;
//    }
//
//    private int saveGenre(String genre) throws SQLException {
//        int genreId = 0;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "insert into genre_catalog (genre)\n" +
//                        "values (?);", Statement.RETURN_GENERATED_KEYS)) {
//
//            preparedStatement.setString(1, genre);
//
//            preparedStatement.executeUpdate();
//
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//
//            if (resultSet.next()) {
//                genreId = resultSet.getInt(1);
//            }
//        }
//        return genreId;
//    }*/