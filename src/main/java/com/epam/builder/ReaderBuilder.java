package com.epam.builder;

import com.epam.model.Employee;
import com.epam.model.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderBuilder implements Builder<Reader> {
    @Override
    public Reader build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String password = resultSet.getString("surname");
        String login = resultSet.getString("surname");
        String telephoneNumber = resultSet.getString("telephone");

        return new Reader(id, name, surname, login, password, telephoneNumber);
    }
}
