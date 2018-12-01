package com.epam.builder;

import com.epam.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<T extends Entity> {
    T build(ResultSet resultSet) throws SQLException;
}
