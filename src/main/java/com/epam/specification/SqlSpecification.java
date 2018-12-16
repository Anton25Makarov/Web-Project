package com.epam.specification;

import com.epam.builder.Builder;
import com.epam.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SqlSpecification {
//    Optional<T> specify();
//    String toSql();
//    String toSql(Builder<T> builder, String... strings);
//    Optional<T> toSql(Builder<T> builder, Connection connection, String... strings) throws SQLException;

    String toSql();
    List<String> getParameters();
}
