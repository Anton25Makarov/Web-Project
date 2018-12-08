package com.epam.repositpry;


import com.epam.connection.ConnectionPool;
import com.epam.model.Entity;
import com.epam.specification.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AbstractRepository<T extends Entity> implements Repository<T> {
    private Connection connection;

    public AbstractRepository() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        this.connection = connectionPool.getConnection();
    }

    @Override
    public List<T> query(SqlSpecification sqlSpecification) {

        return null;
    }

    @Override
    public Optional<T> queryForSingleResult(SqlSpecification sqlSpecification) {
        return Optional.empty();
    }

}
