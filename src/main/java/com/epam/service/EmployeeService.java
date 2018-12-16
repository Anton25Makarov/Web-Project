package com.epam.service;

import com.epam.connection.ConnectionPool;
import com.epam.model.Employee;
import com.epam.repositpry.AbstractRepository;
import com.epam.repositpry.RepositoryFactory;
import com.epam.specification.SqlSpecification;
import com.epam.specification.FindUserByLoginAndPasswordSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeService {

    public Optional<Employee> login(String login, String password)
            throws SQLException, InterruptedException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        Optional<Employee> employee = employeeRepository.queryForSingleResult(specification);

        connectionPool.returnConnection(connection);

        return employee;
    }
}