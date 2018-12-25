package com.epam.builder;

import com.epam.model.Author;
import com.epam.model.Book;
import com.epam.model.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreBuilder implements Builder<BookGenre> {
    @Override
    public BookGenre build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String genre = resultSet.getString("genre");

        return new BookGenre(id, genre);
    }
}
