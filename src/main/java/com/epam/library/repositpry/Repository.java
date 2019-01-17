package com.epam.library.repositpry;

import com.epam.library.specification.SqlSpecification;
import com.epam.library.model.Entity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    List<T> query(SqlSpecification sqlSpecification) throws SQLException;

    Optional<T> queryForSingleResult(SqlSpecification sqlSpecification) throws SQLException;

    void save(T entity) throws SQLException;

    void remove(T entity) throws SQLException;

}
