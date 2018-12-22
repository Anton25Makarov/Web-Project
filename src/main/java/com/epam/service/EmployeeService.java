package com.epam.service;

import com.epam.connection.ConnectionPool;
import com.epam.model.Book;
import com.epam.model.Employee;
import com.epam.repositpry.AbstractRepository;
import com.epam.repositpry.RepositoryFactory;
import com.epam.specification.FindAllBookSpecification;
import com.epam.specification.FindAllSpecification;
import com.epam.specification.SqlSpecification;
import com.epam.specification.FindUserByLoginAndPasswordSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    public Optional<Employee> login(String login, String password)
            throws SQLException, InterruptedException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        Optional<Employee> employee = employeeRepository.queryForSingleResult(specification);

        connectionPool.returnConnection(connection);

        return employee;
    }

    public List<Book> takeBooks() throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        SqlSpecification specification = new FindAllBookSpecification();
        List<Book> books = bookRepository.query(specification);

        connectionPool.returnConnection(connection);
        return books;
    }
}