package com.epam.builder;

import com.epam.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBuilder implements Builder {
    @Override
    public Employee build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String login = resultSet.getString("surname");
        String password = resultSet.getString("surname");
        boolean admin = resultSet.getBoolean("is_admin");

        return new Employee(id, name, surname, login, password, admin);
    }
}
