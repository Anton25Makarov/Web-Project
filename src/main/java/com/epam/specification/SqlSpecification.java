package com.epam.specification;

import com.epam.builder.Builder;
import com.epam.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface SqlSpecification<T extends Entity> {
    Optional<T> specify();

    //    String toSql(Builder<T> builder, String... strings);
    Optional<T> toSql(Builder<T> builder, Connection connection, String... strings) throws SQLException;
}
