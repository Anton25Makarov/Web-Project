package com.epam.library.builder;

import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBuilder implements Builder<Book> {
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long authorId = resultSet.getLong("book_author_id");
        Long genreId = resultSet.getLong("genre_catalog_id");
        String title = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        int year = resultSet.getInt("year");
        int count = resultSet.getInt("count");
        String authorName = resultSet.getString("name");
        String authorSurname = resultSet.getString("surname");

        Author author = new Author(authorId, authorName, authorSurname);
        BookGenre bookGenre = new BookGenre(genreId, genre);

        return new Book(id, title, year, count, author, bookGenre);
    }
}
