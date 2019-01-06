package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.EmployeeBuilder;
import com.epam.library.model.Employee;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository extends AbstractRepository<Employee> {
    private static final String SELECT_QUERY = "select * from employee ";

    public EmployeeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employee> query(SqlSpecification userSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Employee> queryForSingleResult(SqlSpecification specification) throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Employee> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Employee employee) throws SQLException {
        throw new UnsupportedOperationException();
    }

    protected Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }
}
//map<Str, obj> fields
// fields.put(NAME_OF_COLUMN, employee.getId);
