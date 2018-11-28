package com.epam.builder;

import com.epam.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
