package com.epam.repositpry;


import com.epam.builder.Builder;
import com.epam.connection.ConnectionPool;
import com.epam.model.Entity;
import com.epam.specification.SqlSpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    protected Connection connection;

    public AbstractRepository() throws IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        this.connection = connectionPool.takeConnection();
    }

    @Override
    public List<T> query(SqlSpecification sqlSpecification) {
        return null;
    }

    @Override
    public Optional<T> queryForSingleResult(SqlSpecification sqlSpecification, String... sqlValues) throws SQLException {
        return Optional.empty();
    }

    protected abstract Builder<T> getBuilder();

}
