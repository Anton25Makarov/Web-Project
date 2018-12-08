package com.epam.specification;

import com.epam.builder.Builder;
import com.epam.builder.EmployeeBuilder;
import com.epam.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FindEmployeeByLoginAndPasswordSpecification implements SqlSpecification<Employee> {
    private static final String QUERY_SELECT = "select * from librarian where login = ? and password = ?;";
    private String login;
    private String password;

    public FindEmployeeByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Optional<Employee> specify() {
        // find employee in BD


        Employee employee = new Employee(1L, "John", "Smith",
                "J12345", "qwerty", true);
        return Optional.of(employee);
    }

    @Override
    public Optional<Employee> toSql(Builder<Employee> employeeBuilder, Connection connection, String... strings)
            throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee = null;
            if (resultSet.next()) {
                employee = employeeBuilder.build(resultSet);

            }

            return Optional.ofNullable(employee);
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }
    }
}
