package com.epam.library.specification;

import java.util.List;

public interface SqlSpecification {
//    Optional<T> specify();
//    String toSql();
//    String toSql(Builder<T> builder, String... strings);
//    Optional<T> toSql(Builder<T> builder, Connection connection, String... strings) throws SQLException;

    String toSql();
    List<String> getParameters();
}
