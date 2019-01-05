package com.epam.library.builder;

import com.epam.library.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorBuilder implements Builder<Author> {
    @Override
    public Author build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");

        return new Author(id, name, surname);
    }
}
