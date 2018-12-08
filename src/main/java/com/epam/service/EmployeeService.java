package com.epam.service;

import com.epam.connection.ConnectionPool;
import com.epam.model.Employee;
import com.epam.repositpry.AbstractRepository;
import com.epam.repositpry.RepositoryFactory;
import com.epam.specification.SqlSpecification;
import com.epam.specification.FindEmployeeByLoginAndPasswordSpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeService {

    public Optional<Employee> login(String login, String password) throws SQLException, IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        AbstractRepository employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindEmployeeByLoginAndPasswordSpecification(login, password);

        Optional<Employee> employee = employeeRepository.queryForSingleResult(specification);

        connectionPool.returnConnection(connection);

        return employee;
    }
}