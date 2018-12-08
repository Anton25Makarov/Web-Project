package com.epam.repositpry;

import com.epam.builder.Builder;
import com.epam.builder.ReaderBuilder;
import com.epam.model.Entity;
import com.epam.model.Reader;
import com.epam.specification.SqlSpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderRepository extends AbstractRepository<Entity> {


    public ReaderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Entity> query(SqlSpecification readerSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Entity> queryForSingleResult(SqlSpecification userSqlSpecification, String... sqlValues)
            throws SQLException {
        Builder<Entity> readerBuilder = getBuilder();

        return userSqlSpecification.toSql(readerBuilder, connection, sqlValues);
    }

    @Override
    protected Builder<Entity> getBuilder() {
        return new ReaderBuilder();
    }
}
