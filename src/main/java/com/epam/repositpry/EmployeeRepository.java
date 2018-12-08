package com.epam.repositpry;

import com.epam.builder.Builder;
import com.epam.builder.EmployeeBuilder;
import com.epam.model.Entity;
import com.epam.specification.SqlSpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository extends AbstractRepository<Entity> {

    public EmployeeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Entity> query(SqlSpecification userSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Entity> queryForSingleResult(SqlSpecification userSqlSpecification, String... sqlValues)
            throws SQLException {
        Builder<Entity> employeeBuilder = getBuilder();
        return userSqlSpecification.toSql(employeeBuilder, connection, sqlValues);
    }

    protected Builder<Entity> getBuilder() {
        return new EmployeeBuilder();
    }
}
