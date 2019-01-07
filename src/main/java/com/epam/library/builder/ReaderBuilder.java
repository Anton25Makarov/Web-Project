package com.epam.library.builder;

import com.epam.library.model.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderBuilder implements Builder<Reader> {
    @Override
    public Reader build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String telephoneNumber = resultSet.getString("telephone");

        return new Reader(id, name, surname, login, password, telephoneNumber);
    }
}
