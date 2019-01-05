package com.epam.library.repositpry;


import com.epam.library.builder.Builder;
import com.epam.library.specification.SqlSpecification;
import com.epam.library.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    protected Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    /*private void takeConnection() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void returnConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.returnConnection(connection);
    }*/

    @Override
    public List<T> query(SqlSpecification sqlSpecification) throws SQLException {
        // ret execCuerry
        return null;
    }

    @Override
    public Optional<T> queryForSingleResult(SqlSpecification sqlSpecification) throws SQLException {
        return Optional.empty();
    }
    // get table name?
    
    @Override
    public abstract boolean save(T t) throws SQLException;

    @Override
    public abstract boolean remove(T t) throws SQLException;
    
    protected abstract Builder<T> getBuilder();


}
