package com.epam.library.builder;

import com.epam.library.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBuilder implements Builder<Employee> {
    @Override
    public Employee build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        boolean admin = resultSet.getBoolean("is_admin");

        return new Employee(id, name, surname, login, password, admin);
    }
}
