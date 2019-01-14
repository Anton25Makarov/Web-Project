package com.epam.library.builder;

import com.epam.library.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderBuilder implements Builder<Order> {
    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
//        Long authorId = resultSet.getLong("book_author_id");
//        Long bookGenreId = resultSet.getLong("genre_catalog_id");
        boolean inReadingRoom = resultSet.getBoolean("is_in_reading_room");
        Date takingDate = resultSet.getDate("taking_date");
        Date returnDate = resultSet.getDate("return_date");
        Long bookId = resultSet.getLong("book_id");
        Long readerId = resultSet.getLong("reader_id");
//        String bookTitle = resultSet.getString("title");
//        int bookCount = resultSet.getInt("count");
//        int bookYear = resultSet.getInt("year");
//        String authorName = resultSet.getString("name");
//        String authorSurname = resultSet.getString("surname");
//        String bookGenre = resultSet.getString("genre");
//        String readerLogin = resultSet.getString("login");


//        Author author = new Author(authorId, authorName, authorSurname);
//        BookGenre genre = new BookGenre(bookGenreId, bookGenre);

        Book book = new Book(bookId);
        Reader reader = new Reader(readerId);

        return new Order(id, inReadingRoom, takingDate, returnDate, book, reader);
    }
}
