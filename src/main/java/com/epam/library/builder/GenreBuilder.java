package com.epam.library.builder;

import com.epam.library.model.BookGenre;

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
