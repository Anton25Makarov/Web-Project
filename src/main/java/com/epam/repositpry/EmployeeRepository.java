package com.epam.repositpry;

import com.epam.builder.Builder;
import com.epam.builder.EmployeeBuilder;
import com.epam.model.Employee;
import com.epam.specification.SqlSpecification;

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

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<Employee> queryForSingleResult(SqlSpecification specification)
            throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Employee> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    protected Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }

    private Optional<Employee> executeQueryForSingleResult(
            Builder<Employee> builder, String query, List<String> parameters) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter : parameters) {
                preparedStatement.setString(i++, parameter);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee = null;
            if (resultSet.next()) {
                employee = builder.build(resultSet);
            }

            return Optional.ofNullable(employee);
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }
    }
}
