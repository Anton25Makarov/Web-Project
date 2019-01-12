package com.epam.library.builder;

import com.epam.library.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderBuilder implements Builder<Order> {
    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        boolean inReadingRoom = resultSet.getBoolean("is_in_reading_room");
        Date takingDate = resultSet.getDate("taking_date");
        Date returnDate = resultSet.getDate("return_date");
        Long bookId = resultSet.getLong("book_id");
        Long readerId = resultSet.getLong("reader_id");

        Book book = new Book(bookId);
        Reader reader = new Reader(readerId);

        return new Order(id, inReadingRoom, takingDate, returnDate, book, reader);
    }
}
