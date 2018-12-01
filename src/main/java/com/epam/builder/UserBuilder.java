package com.epam.builder;

import com.epam.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder {
    @Override
    public Employee build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
