package com.epam.builder;

import com.epam.model.Author;
import com.epam.model.Book;
import com.epam.model.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBuilder implements Builder<Book> {
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        int year = resultSet.getInt("year");
        int count = resultSet.getInt("count");
        String authorName = resultSet.getString("name");
        String authorSurname = resultSet.getString("surname");

        Author author = new Author(authorName, authorSurname);
        BookGenre bookGenre = new BookGenre(genre);

        return new Book(id, title, year, count, author, bookGenre);
    }
}
